package com.example.learningapptry2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.learningapptry2.chaptertopics.ExpandableHeightGridView;
import com.example.learningapptry2.chaptertopics.TopicAdapter;

public class TopicActivity extends AppCompatActivity {
    Toolbar toolbar;
    String arr[] = null;
    ImageView chapterImage;
    ExpandableHeightGridView gridView;
    String chapterName;
    TopicAdapter adapter;
    String module01[] = {"Module 01 Introduction", "Cloud Computing"};
    String module02[] = {"Module 02 Introduction", "EC2 Instance types", "EC2 Pricing", "Scaling EC2", "ELB", "Messaging & Queuing", "Additional Compute Services", "Summary"};
    String module03[] = {"Global Infrastructure", "Edge Locations", "Provision AWS Resources", "Summary"};
    String module04[] = {"Module 04 Introduction", "Connectivity", "Subnets and Network Control", "Global Networking"};
    String module05[] = {"Amazon EBS", "Amazon S3", "Amazon EFS", "Amazon RDS", "Amazon DynamoDB", "Amazon Redshift", "Amazon Data Migration", "Additional DB Services"};
    String module06[] = {"Module 06 Introduction", "Shared Responsibility Model", "User Permissions and Access", "AWS Organizations", "Compliance", "DOS Attacks", "Additional Security Services"};
    String module07[] = {"Module 07 Introduction", "Amazon CloudWatch", "AWS CloudTrail", "AWS Trusted Advisor"};
    String module08[] = {"Module 08 Introduction", "Amazon Free Tier", "Pricing Concepts", "Billing Dashboard", "Consolidated Billing", "AWS Budgets", "AWS Cost Explorer", "AWS Support Plans", "AWS Market Plans"};
    String module09[] = {"Module 09 Introduction", "AWS CAF", "Migration Strategies", "AWS Snow Family"};
    String module10[] = {"Module 10 Introduction", "AWS Well-Architected Framework", "Benefits of AWS Cloud"};
    String module11[] = {"Module 11 Introduction", "Exam Details", "Exam Strategies"};
    String finalAssessment[] = {"Final Assessment"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        toolbar = findViewById(R.id.toolbar);
        gridView = findViewById(R.id.topics_name);
        gridView.setExpanded(true);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        chapterName = getIntent().getStringExtra("chapterName");
        chapterImage = findViewById(R.id.chapter_image);

        compareAndOpen();
    }

    private void compareAndOpen() {

        if  (chapterName.equals("Module 01")) {
            arr = module01;
            chapterImage.setImageResource(R.drawable.module_one);
            getSupportActionBar().setTitle("Module 01 Introduction to AWS");
        }else if (chapterName.equals("Module 02")) {
            arr = module02;
            chapterImage.setImageResource(R.drawable.module_two);
            getSupportActionBar().setTitle("Module 02 Compute in the Cloud");
        }else if (chapterName.equals("Module 03")){
            arr = module03;
            chapterImage.setImageResource(R.drawable.module_three);
            getSupportActionBar().setTitle("Module 03 Global Infra and Reliability");
        }else if (chapterName.equals("Module 04")){
            arr = module04;
            chapterImage.setImageResource(R.drawable.module_four);
            getSupportActionBar().setTitle("Module 04 Networking");
        }else if (chapterName.equals("Module 05")){
            arr = module05;
            chapterImage.setImageResource(R.drawable.module_five);
            getSupportActionBar().setTitle("Module 05 Storage and Databases");
        }else if (chapterName.equals("Module 06")){
            arr = module06;
            chapterImage.setImageResource(R.drawable.module_six);
            getSupportActionBar().setTitle("Module 06 Security");
        }else if (chapterName.equals("Module 07")) {
            arr = module07;
            chapterImage.setImageResource(R.drawable.module_seven);
            getSupportActionBar().setTitle("Module 07 Monitoring & Analysis");
        }else if (chapterName.equals("Module 08")) {
            arr = module08;
            chapterImage.setImageResource(R.drawable.module_eight);
            getSupportActionBar().setTitle("Module 08 Pricing & Support");
        }else if (chapterName.equals("Module 09")) {
            arr = module09;
            chapterImage.setImageResource(R.drawable.module_nine);
            getSupportActionBar().setTitle("Module 09 Migration & Innovation");
        }else if (chapterName.equals("Module 10")) {
            arr = module10;
            chapterImage.setImageResource(R.drawable.module_ten);
            getSupportActionBar().setTitle("Module 10 The Cloud Journey");
        } else if (chapterName.equals("Module 11")) {
            arr = module11;
            chapterImage.setImageResource(R.drawable.module_eleven);
            getSupportActionBar().setTitle("Module 11 Cloud Practitioner Basics");
        } else if (chapterName.equals("Final Assessment")) {
            arr = finalAssessment;
            chapterImage.setImageResource(R.drawable.final_assessment);
            getSupportActionBar().setTitle("Final Assessment");
        } else {
            arr = null;
        }

        adapter = new TopicAdapter(arr, TopicActivity.this);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(TopicActivity.this, arr[position], Toast.LENGTH_SHORT).show();
                String selectedTopic = arr[position];  // Assuming 'arr' contains topic names
                String htmlFile = "";

                if (selectedTopic.equals("Module 01 Introduction")) {
                    htmlFile = "Module_01/01.html";
                } else if (selectedTopic.equals("Cloud Computing")) {
                    htmlFile = "Module_01/02.html";
                } else if (selectedTopic.equals("Module 02 Introduction")) {
                    htmlFile = "Module_02/01.html";
                } else if (selectedTopic.equals("EC2 Instance types")) {
                    htmlFile = "Module_02/02.html";
                } else if (selectedTopic.equals("EC2 Pricing")) {
                    htmlFile = "Module_02/03.html";
                } else if (selectedTopic.equals("Scaling EC2")) {
                    htmlFile = "Module_02/04.html";
                }

                if (!htmlFile.isEmpty()) {
                    loadFragment(WebViewFragment.newInstance(htmlFile));
                }
            }
        });

    }
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)  // 'fragment_container' is your FrameLayout
                .addToBackStack(null)
                .commit();
    }

}