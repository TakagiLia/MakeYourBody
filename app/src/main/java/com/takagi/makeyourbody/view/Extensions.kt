package com.takagi.makeyourbody.view

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

/**
 * OSの戻るボタンの無効化
 */

//メモ：拡張関数　既存のクラスに対して継承したクラスを新規作成する必要なく、新たなメソッドを作成することが可能
//例としてFragmentの継承クラスで呼び出せる拡張関数を作成
fun Fragment.disabledBackPressed() {

    //拡張関数の中は拡張元クラスのスコープ内になる
    requireActivity()
        .onBackPressedDispatcher
        .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 何もしない（戻るボタンの無効化）
            }
        })
}
