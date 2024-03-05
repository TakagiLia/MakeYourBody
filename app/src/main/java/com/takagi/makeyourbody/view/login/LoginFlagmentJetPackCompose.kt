package com.takagi.makeyourbody.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.takagi.makeyourbody.R

/*JetPackComposeでのレイアウトをドラフトで作成（ログイン画面）*/
class LoginFragmentJetPackCompose : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                //LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen() {

    var userName by remember { mutableStateOf("") }

    var passWord by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //ログイン画面トップ画
        Image(
            painter = painterResource(id = R.drawable.login_logo_new),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        //ログインはこちら
        Text(text = stringResource(R.string.login_title), fontSize = 16.sp)

        //ユーザとパスワードの入力エリア
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)

        ) {

            Spacer(modifier = Modifier.height(40.dp))

            //ユーザ名
            CustomTextField(
                userName,
                stringResource(R.string.login_username),
                KeyboardType.Email,
                VisualTransformation.None
            )

            Spacer(modifier = Modifier.height(16.dp))

            //パスワード
            CustomTextField(
                passWord, stringResource(R.string.login_password), KeyboardType.Password,
                PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

        }

        //ログインボタン
        CustomButton(stringResource(R.string.login_btn_label)) {}

        Spacer(modifier = Modifier.height(80.dp))

        //サインアップ説明テキスト
        Text(text = stringResource(R.string.login_signup_msg), fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))

        //サインアップボタン
        CustomButton(stringResource(R.string.login_signup_btn_label), {})

    }
}

/*TODO　Componentとして別ファイルに切り分ける*/
@Composable
fun CustomTextField(
    value: String,
    placeholder: String,
    keyboard: KeyboardType,
    visual: VisualTransformation
) {

    var text by remember { mutableStateOf(value) }

    BasicTextField(
        value = text,
        onValueChange = { text = it },
        keyboardOptions = KeyboardOptions(keyboardType = keyboard),
        visualTransformation = visual,
        modifier = Modifier
            .background(color = colorResource(R.color.pale_red_color).copy(alpha = 0.3f))
            .padding(6.dp)
            .fillMaxWidth(),
        decorationBox = { innerTextField ->
            Row(Modifier.padding(8.dp)) {
                if (text.isEmpty()) {
                    Text(
                        placeholder,
                        color = Color.Gray,
                        fontSize = 20.sp
                    ) // プレースホルダーのテキスト
                }
                innerTextField()
            }
        },
        textStyle = TextStyle(fontSize = 20.sp),
    )
}

/*TODO　Componentとして別ファイルに切り分ける*/
@Composable
fun CustomButton(buttonLabel: String, onClick: () -> Unit) {

    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .width(250.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary_color))
    ) {
        Text(
            text = buttonLabel,
            modifier = Modifier.padding(10.dp),
            fontSize = 20.sp
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}