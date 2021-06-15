package com.room.database.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.room.database.Constants;
import com.room.database.R;
import com.room.database.asynctask.StudentRepository;
import com.room.database.local.Student;
import com.room.database.utils.ActivityUtility;
import com.room.database.utils.BitmapManager;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ItemViewHolder> {

    private Activity activityContext;
    private LayoutInflater layoutInflater;
    private List<Student> itemList;
    private StudentRepository studentRepository;

    public StudentAdapter(AppCompatActivity activityContext,StudentRepository studentRepository) {
        this.activityContext    = activityContext;
        itemList                = new ArrayList<>();
        layoutInflater          = (LayoutInflater) activityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.studentRepository  = studentRepository;
    }

    public void setAdapterListData(List<Student> recyclerViewItemModelArrayList) {
        this.itemList = recyclerViewItemModelArrayList;
        notifyDataSetChanged();
    }

    public List<Student> getAdapterListData() {
        return this.itemList;
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    public boolean isAdapterListEmpty() {
        return getItemCount() == 0;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.student_activity_recycler_view_row_desing, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int position) {
        Student currentItem = itemList.get(position);
        ((ItemViewHolder) itemViewHolder).setData(currentItem);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView passportPhotoImageView;
        private TextView nameTextView,branchTextView,rollNumberTextView,gradeTextView,phoneNumberTextView;
        private Button updateButton,deleteButton;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            passportPhotoImageView  = itemView.findViewById(R.id.passport_photo_image_view);
            nameTextView            = itemView.findViewById(R.id.name_text_view);
            branchTextView          = itemView.findViewById(R.id.branch_text_view);
            rollNumberTextView      = itemView.findViewById(R.id.roll_number_text_view);
            gradeTextView           = itemView.findViewById(R.id.grade_text_view);
            phoneNumberTextView     = itemView.findViewById(R.id.phone_number_text_view);

            updateButton = itemView.findViewById(R.id.update_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }

        public void setData(Student recyclerViewItemModel) {
            passportPhotoImageView.setImageBitmap(BitmapManager.byteToBitmap(recyclerViewItemModel.getPassportPhoto()));
            nameTextView.setText(recyclerViewItemModel.getFirstName()+" "+recyclerViewItemModel.getLastName());
            branchTextView.setText(recyclerViewItemModel.getBranch());
            rollNumberTextView.setText(recyclerViewItemModel.getRollNumber());
            gradeTextView.setText(recyclerViewItemModel.getGrade());
            phoneNumberTextView.setText(recyclerViewItemModel.getPhoneNumber());

            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putLong(Constants.ID,recyclerViewItemModel.getId());
                    ActivityUtility.goToNextActivity(activityContext, UpdateStudentActivity.class,bundle);
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activityContext);
                    builder.setMessage("Are you sure you want to delete ?")
                            .setCancelable(false)
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                   studentRepository.deleteTask(recyclerViewItemModel);
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
    }
}