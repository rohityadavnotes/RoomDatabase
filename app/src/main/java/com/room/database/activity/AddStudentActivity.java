package com.room.database.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.room.database.Constants;
import com.room.database.R;
import com.room.database.asynctask.StudentRepository;
import com.room.database.local.Student;
import com.room.database.utils.BitmapManager;

import java.util.Objects;

public class AddStudentActivity extends BaseActivity {

    private static final String TAG = AddStudentActivity.class.getSimpleName();

    private ImageView passportPhotoImageView;

    private TextInputLayout firstNameTextInputLayout;
    private TextInputLayout lastNameTextInputLayout;
    private TextInputLayout branchTextInputLayout;
    private TextInputLayout rollNumberTextInputLayout;
    private TextInputLayout gradeTextInputLayout;
    private TextInputLayout phoneNumberTextInputLayout;

    private TextInputEditText firstNameTextInputEditText;
    private TextInputEditText lastNameTextInputEditText;
    private TextInputEditText branchTextInputEditText;
    private TextInputEditText rollNumberTextInputEditText;
    private TextInputEditText gradeTextInputEditText;
    private TextInputEditText phoneNumberTextInputEditText;

    private Button addButton,cancelButton;

    private String firstNameString,lastNameString,branchString,rollNumberString,gradeString,contactNumberString;
    private byte[] studentImageByteArray;

    private StudentRepository studentRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_add_student);
    }

    @Override
    protected void initializeView() {
        passportPhotoImageView      =  findViewById(R.id.passport_photo_image_view);

        firstNameTextInputLayout    =  findViewById(R.id.first_name_text_input_layout);
        lastNameTextInputLayout     =  findViewById(R.id.last_name_text_input_layout);
        branchTextInputLayout       =  findViewById(R.id.branch_text_input_layout);
        rollNumberTextInputLayout   =  findViewById(R.id.roll_number_text_input_layout);
        gradeTextInputLayout        =  findViewById(R.id.grade_text_input_layout);
        phoneNumberTextInputLayout  =  findViewById(R.id.phone_number_text_input_layout);

        firstNameTextInputEditText  =  findViewById(R.id.first_name_text_input_edit_text);
        lastNameTextInputEditText   =  findViewById(R.id.last_name_text_input_edit_text);
        branchTextInputEditText     =  findViewById(R.id.branch_text_input_edit_text);
        rollNumberTextInputEditText =  findViewById(R.id.roll_number_text_input_edit_text);
        gradeTextInputEditText      =  findViewById(R.id.grade_text_input_edit_text);
        phoneNumberTextInputEditText=  findViewById(R.id.phone_number_text_input_edit_text);

        addButton                   =  findViewById(R.id.add_button);
        cancelButton                =  findViewById(R.id.cancel_button);
    }

    @Override
    protected void initializeObject() {
        studentRepository = new StudentRepository(this);
    }

    @Override
    protected void onTextChangedListener() {

    }

    @Override
    protected void initializeEvent() {

        passportPhotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser(Constants.PICK_IMAGE_REQUEST_CODE_ADD);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation())
                {
                    firstNameString     = Objects.requireNonNull(firstNameTextInputEditText.getText()).toString();
                    lastNameString      = Objects.requireNonNull(lastNameTextInputEditText.getText()).toString();
                    branchString        = Objects.requireNonNull(branchTextInputEditText.getText()).toString();
                    rollNumberString    = Objects.requireNonNull(rollNumberTextInputEditText.getText()).toString();
                    gradeString         = Objects.requireNonNull(gradeTextInputEditText.getText()).toString();
                    contactNumberString = Objects.requireNonNull(phoneNumberTextInputEditText.getText()).toString();

                    Student student = new Student(firstNameString,lastNameString,branchString,rollNumberString,gradeString,contactNumberString,studentImageByteArray);
                    studentRepository.insertTask(student);
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public boolean validation()
    {
        if (Objects.requireNonNull(firstNameTextInputEditText.getText()).toString().length() < 1)
        {
            String message = "Please enter your first name !";
            firstNameTextInputLayout.setError(message);
            return false;
        }
        if (Objects.requireNonNull(lastNameTextInputEditText.getText()).toString().length() < 1)
        {
            String message = "Please enter your last name !";
            lastNameTextInputLayout.setError(message);
            return false;
        }
        if (Objects.requireNonNull(branchTextInputEditText.getText()).toString().length() < 1)
        {
            String message = "Please enter your branch name !";
            branchTextInputLayout.setError(message);
            return false;
        }
        if (Objects.requireNonNull(rollNumberTextInputEditText.getText()).toString().length() < 1)
        {
            String message = "Please enter your roll number !";
            rollNumberTextInputLayout.setError(message);
            return false;
        }
        if (Objects.requireNonNull(gradeTextInputEditText.getText()).toString().length() < 1)
        {
            String message = "Please enter your grade !";
            gradeTextInputLayout.setError(message);
            return false;
        }
        if (Objects.requireNonNull(phoneNumberTextInputEditText.getText()).toString().length()!=10)
        {
            String message = "Please enter your contact number !";
            phoneNumberTextInputLayout.setError(message);
            return false;
        }
        return true;
    }

    private void showFileChooser(int SELECT_REQUEST_CODE) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_REQUEST_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            if (requestCode == Constants.PICK_IMAGE_REQUEST_CODE_ADD)
            {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {

                    Bitmap studentImageBitmap= BitmapManager.decodeUri(AddStudentActivity.this,selectedImageUri, 400);
                    studentImageByteArray = BitmapManager.bitmapToByte(studentImageBitmap);
                    passportPhotoImageView.setImageBitmap(BitmapManager.byteToBitmap(studentImageByteArray));
                }
            }
        }
    }
}
