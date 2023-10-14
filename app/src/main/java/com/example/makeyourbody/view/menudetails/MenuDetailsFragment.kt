package com.example.makeyourbody.view.menudetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.makeyourbody.NiftyCloudApiClient
import com.example.makeyourbody.R
import com.example.makeyourbody.data.TrainingItem
import com.example.makeyourbody.data.TrainingMenu
import com.example.makeyourbody.databinding.FragmentMenuDetailsBinding
import com.example.makeyourbody.view.dialog.DatePickerFragment
import com.example.makeyourbody.view.exercisedetails.ExerciseDetailsViewModel
import com.example.makeyourbody.view.maketrainingmenu.EditTrainingListViewModel
import com.example.makeyourbody.view.maketrainingmenu.edittraininglist.EditTrainingListAdapter
import com.example.makeyourbody.view.signup.TrainingType
import com.example.makeyourbody.view.traininglist.TrainingListFragment
import com.nifcloud.mbaas.core.NCMBUser

class MenuDetailsFragment :Fragment() {

    private var _binding: FragmentMenuDetailsBinding? = null
    private val binding get() = _binding!!

    private var editBtnFlg : Boolean = false
    private var menuDetailsItemListBefore = emptyList<TrainingItem>()

    private val userType = NCMBUser.currentuser?.get("trainingType").toString()

    //トレーニングメニュー詳細情報保持用
    private val menuDetailsViewModel: MenuDetailsViewModel by activityViewModels()

    //トレーニングアイテム詳細ページ表示用
    private val exerciseDetailsViewModel: ExerciseDetailsViewModel by activityViewModels()

    //トレーニングアイテム編集リスト用
    private val editTrainingListViewModel: EditTrainingListViewModel by activityViewModels()

    //リストアイテム詳細ページ遷移
    private val onClickInfoBtn: (TrainingItem)-> Unit = { trainingItem ->
        Log.d("--onClickInfoBtn--","詳細ボタン押下")
        exerciseDetailsViewModel.setItem(trainingItem)
        findNavController().navigate(R.id.action_menu_details_to_exercise_details_screen)
    }

    //リストアイテム削除処理
    private val onItemClick: (TrainingItem) -> Unit = { trainingItem ->
        editTrainingListViewModel.deleteSelectedItems(trainingItem)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fixingList =
            NiftyCloudApiClient().trainingItemFixList(menuDetailsViewModel.menu.value?.menuContent.toString())
        binding.apply {
            //初期表示設定
            menuDetailsDate.setText(menuDetailsViewModel.menu.value?.menuDate)
            menuDetailsTarget.setText(menuDetailsViewModel.menu.value?.menuTarget)
            var menuDetailsItemList = NiftyCloudApiClient().getTrainingItemBindingMenu(fixingList) ?: emptyList()
            editTrainingListViewModel.setSelectedItems(menuDetailsItemList)

            Log.d("--初期トレーニングアイテム--",menuDetailsItemList.toString())
            menuDetailsList.adapter = MenuDetailsListAdapter(menuDetailsItemList,onClickInfoBtn)

            //トレーニーの場合は編集できないようにする
            if (userType == TrainingType.TRAINEE.type) menuDetailsEditBtn.visibility = View.INVISIBLE

            //トレーニングアイテムリストの監視
            editTrainingListViewModel.selectedItems.observe(viewLifecycleOwner){
                Log.d("--トレーニングアイテムリスト監視--","")
                menuDetailsItemList = editTrainingListViewModel.selectedItems.value?.toList() ?: emptyList()
                if(editBtnFlg){
                    Log.d("--監視editBtnFlg--","①")
                    menuDetailsList.adapter =
                        EditTrainingListAdapter(menuDetailsItemList,onItemClick,editTrainingListViewModel)
                }else{
                    Log.d("--監視editBtnFlg--","②")
                    menuDetailsList.adapter =
                        MenuDetailsListAdapter(menuDetailsItemList, onClickInfoBtn)

                }
            }

            //日付ダイアログ
            menuDetailsDate.setOnClickListener{

                DatePickerFragment().show(
                    childFragmentManager,
                    DatePickerFragment::class.java.name
                )
                //日付ダイアログでOK押下の際DatePickerFragmentからの値受け取り
                childFragmentManager.setFragmentResultListener(
                    "request_key",
                    viewLifecycleOwner
                ) { _, result: Bundle ->
                    result.getString("date_picker_value")
                        ?.let {
                            menuDetailsViewModel.setMenuDate(it)
                            menuDetailsDate.setText(it)
                        }
                    }

            }

            //編集切り替えボタン押下時の処理
            menuDetailsEditBtn.setOnClickListener { view ->
                //日付、対象者項目編集可能にする　トレーニングメニュー選択ボタン活性
                menuDetailsDate.isEnabled = !menuDetailsDate.isEnabled
                menuDetailsTarget.isEnabled = !menuDetailsTarget.isEnabled
                menuDetailsSelectBtn.isEnabled = !menuDetailsSelectBtn.isEnabled
                menuDetailsSelectBtn.visibility = View.VISIBLE

                //編集可否フラグの切り替え
                editBtnFlg = !editBtnFlg

                //リストを編集可能用または表示用に切り替える
                if(editBtnFlg){
                    Log.d("--editBtnFlg--","①")
                    menuDetailsEditBtn.setText(R.string.menu_save_btn_label)
                    menuDetailsItemListBefore = menuDetailsItemList
                    menuDetailsList.adapter =
                        EditTrainingListAdapter(menuDetailsItemList,onItemClick,editTrainingListViewModel)
                }else{
                    Log.d("--editBtnFlg--","②")
                    //ボタンやトレーニングボタンを非表示にする
                    menuDetailsEditBtn.setText(R.string.menu_edit_btn_label)
                    menuDetailsSelectBtn.visibility = View.INVISIBLE

                    //編集されたか確認　　ここのIf文や保存時の処理の書き方よくなさそうなのでそのうち改修
                    if(editTrainingListViewModel.menuDate.value != menuDetailsDate.text.toString() ||
                        editTrainingListViewModel.menuTargetUser.value != menuDetailsTarget.text.toString() ||
                        editTrainingListViewModel.selectedItems.value?.size != menuDetailsItemListBefore.size){
                        Log.d("--Edit Complete--","Saveに走る")

                        //保存のため再度表示されているリストの要素データを集める
                        val trainingItemContent = editTrainingListViewModel.selectedItems.value?.map { selectedItems ->
                            //selectedItems.name
                            selectedItems.objectId
                        }
                        //実際の保存処理
                        val updateTrainingMenu = TrainingMenu(
                            menuDetailsDate.text.toString(),
                            menuDetailsTarget.text.toString(),"",trainingItemContent.toString(),
                            menuDetailsViewModel.menu.value?.objectId.toString())
                        NiftyCloudApiClient().updateTrainingMenu(updateTrainingMenu)

                        //保持データ再セット
                        Log.d("--再セット時--",menuDetailsItemList.toString())
                        menuDetailsItemListBefore = menuDetailsItemList
                        editTrainingListViewModel.setMenuDate(menuDetailsDate.text.toString())
                        editTrainingListViewModel.setTargetUser(menuDetailsTarget.text.toString())
                        menuDetailsItemList = editTrainingListViewModel.selectedItems.value?.toList()?: emptyList()
                        menuDetailsList.adapter =
                            MenuDetailsListAdapter(menuDetailsItemList, onClickInfoBtn)
                    }
                }
            }

            //トレーニング選択ボタンを押下時の処理
            menuDetailsSelectBtn.setOnClickListener {
                TrainingListFragment().show(childFragmentManager,"fragment_list_training_item")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        editTrainingListViewModel.clearSelectedItems()
    }

}