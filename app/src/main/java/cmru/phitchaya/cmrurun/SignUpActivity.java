package cmru.phitchaya.cmrurun;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertController;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText, passwordEditText, userEditText;
    private RadioGroup radioGroup;
    private RadioButton avata0RadioButton, avata1RadioButton,
            avata2RadioButton, avata3RadioButton, avata4RadioButton;
    //ประกาศตัวแปร
    private String nameString, userString,passwordString,  avataString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        nameEditText = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText2);
        passwordEditText = (EditText) findViewById(R.id.editText3);
        radioGroup = (RadioGroup) findViewById(R.id.radAvata);
        avata0RadioButton = (RadioButton) findViewById(R.id.radioButton);
        avata1RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        avata2RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        avata3RadioButton = (RadioButton) findViewById(R.id.radioButton4);
        avata4RadioButton = (RadioButton) findViewById(R.id.radioButton5);

//radio controller
radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.radioButton:
                avataString="0";
                break;
            case R.id.radioButton2:
                avataString="1";
                break;
            case R.id.radioButton3:
                avataString="2";
                break;
            case R.id.radioButton4:
                avataString="3";
                break;
            case R.id.radioButton5:
                avataString="4";
                break;
        }



    }//onChecked
});
//ตรวจสอบว่ามีการเลือกรึเปล่า
    }   // Main Method


    public  void clickSignUpSign(View view){

        //get value form edit text
        nameString =nameEditText.getText().toString().trim();
                //trim() เอาช่องว่างหน้าและหลังออก
                //gettext เอาสิ่งที่กรอกไว้ใน nameEdittext
        userString = userEditText.getText().toString().trim();
        passwordString =passwordEditText.getText().toString().trim();
        //chk space
        //||  = or
        if (nameString.equals("") || userString.equals("") || passwordString.equals("")) {
            //have  space
        MyAlert myAlert=new MyAlert();
            myAlert.myDialog(this,"มีช่องว่าง","กรุณากรอกทุกช่องคะ");

        } else if (checkChooseAvata()) {
            //chked
            confirmData();
        } else {
            //not chked
            MyAlert myAlert=new MyAlert();
            myAlert.myDialog(this,"ยังไม่เลือก avata", "กรุณาเลือก avata ด้วยคะ");

    }//click sign

    private void confirmData() {
        MyData myData = new MyData();
        int[] avataInts = myData.getAvataInts();

        AlertDialog.Builder builer = new AlertDialog.Builder(this);
        builer.setCancelable(false);
        builer.setIcon(avataInts[Integer.parseInt(avataString)]);
        builer.setTitle(nameString);
        builer.setMessage("user=" + userString + "\n" + "Password=" + passwordString);
        builer.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builer.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                uploadUserToServer();
                dialog.dismiss();
            }
        });
        builer.show();
    }//confirmData

    private void uploadUserToServer() {
    }


    private boolean checkChooseAvata() {
        boolean status = true;
        if (avata0RadioButton.isChecked() ||
                avata1RadioButton.isChecked() ||
                avata2RadioButton.isChecked() ||
                avata3RadioButton.isChecked()||
                avata4RadioButton.isChecked()) {
            //have chk
            status =true;

        } else {
            //not choose
            status = false;
        }

        return status;
    }


}   // Main Class