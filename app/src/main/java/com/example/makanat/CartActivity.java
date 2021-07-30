package com.example.makanat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.makanat.model.OrderItemModelClass;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class CartActivity extends AppCompatActivity {
    private ImageView itemImageView;
    private EditText item_nameET;
    private EditText item_desET;
    private EditText item_categoryET;
    private EditText item_ingET;
    private EditText item_conditionET;
    private EditText item_priceET;
    Button addItembtn;
    private Uri mImageUri;
    private String imageAddress="";
    private String catagory="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().hide();
        init_view();

    }

    private void init_view() {
        item_nameET=findViewById(R.id.nameitemET);
        item_desET=findViewById(R.id.itemDescriptionET);
        item_categoryET=findViewById(R.id.categoryTagsET);
        item_ingET=findViewById(R.id.Ingredient_ET);
        item_conditionET=findViewById(R.id.item_condET);
        item_priceET=findViewById(R.id.item_priceET);
        addItembtn=findViewById(R.id.addItem_addBtn);
        itemImageView=findViewById(R.id.thumbnailImageView);
        itemImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        addItembtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUserData();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            uploadImage(mImageUri);
            Picasso.get().load(mImageUri).into(itemImageView);
        }

    }

    private boolean validateUserData() {
        try {
            if (imageAddress.equals("")){
                Toast.makeText(this,"Zero image detected.",Toast.LENGTH_SHORT);
                return false;
            }
            if (item_nameET.getText().toString().equals("")) {
                item_nameET.setError("Please enter item name.");
                return false;
            }
            if (item_desET.getText().toString().equals("")) {
                item_desET.setError("Please enter item description.");
                return false;
            }
            if (catagory.toString().equals("")) {
                Toast.makeText(this,"Please select a category.",Toast.LENGTH_LONG).show();
                return false;
            }
            if (item_ingET.getText().toString().equals("")) {
                item_ingET.setError("Please enter preferred item.");
                return false;
            }
            if (item_conditionET.getText().toString().equals("")) {
                item_conditionET.setError("Please enter item condition.");
                return false;
            }
            if (item_priceET.getText().toString().equals("")) {
                item_priceET.setError("Please enter item quantity.");
                return false;
            }

            OrderItemModelClass orderItemModelClass =new OrderItemModelClass();
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            orderItemModelClass.setTraderID(firebaseUser.getUid());
            orderItemModelClass.setName(item_nameET.getText().toString());
            orderItemModelClass.setItemId("" + System.currentTimeMillis());
            orderItemModelClass.setDescription(item_desET.getText().toString());
            orderItemModelClass.setCategory(catagory.toString());
            orderItemModelClass.setIngredient(item_ingET.getText().toString());
            orderItemModelClass.setPrice(Integer.parseInt(item_priceET.getText().toString()));
            orderItemModelClass.setCondition(item_conditionET.getText().toString());
            orderItemModelClass.setImageUrl(imageAddress);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference().child("order_items");
            databaseReference.child(orderItemModelClass.getItemId()).setValue(orderItemModelClass);
            finish();

        }catch (Exception e){
            Toast.makeText(CartActivity.this," "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return true;
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));

    }

    private void uploadImage(Uri uri) {
        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference("order_items");
        final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                + "." + getFileExtension(mImageUri));

        final UploadTask uploadTask = fileReference.putFile(mImageUri);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();

                        }
                        return fileReference.getDownloadUrl();

                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            try {
                                imageAddress = task.getResult().toString();

                            }catch (Exception e){
                                Toast.makeText(CartActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        RadioGroup group=findViewById(R.id.radio_group1);
        RadioGroup group2=findViewById(R.id.radio_group2);
        boolean checked = ((RadioButton) view).isChecked();
        String[] catagorys = new String[] {"All", "Western", "Eastern", "Dessert",
                "Local", "Drinks", "Snacks"};
        group.clearCheck();
        group2.clearCheck();
        ((RadioButton) view).setChecked(true);
        switch(view.getId()) {
            case R.id.radioButtonWestern:
                if (checked)
                    catagory=catagorys[1];

                break;
            case R.id.radioButtonEastern:
                if (checked)
                    catagory=catagorys[2];
                break;

            case R.id.radioButtonDessert:
                if (checked)
                    catagory=catagorys[3];

                break;
            case R.id.radioButtonLocal:
                if (checked)
                    catagory=catagorys[4];
                break;
            case R.id.radioButtonDrink:
                if (checked)
                    catagory=catagorys[5];

                break;
            case R.id.radioButtonSnacks:
                if (checked)
                    catagory=catagorys[6];
                break;
        }
    }


}