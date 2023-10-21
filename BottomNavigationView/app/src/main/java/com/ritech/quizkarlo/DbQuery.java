package com.ritech.quizkarlo;

import android.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;
import com.ritech.quizkarlo.Interface.MyCompleteListener;
import com.ritech.quizkarlo.Models.CategoryModel;
import com.ritech.quizkarlo.Models.ProfileModel;
import com.ritech.quizkarlo.Models.QuestionModel;
import com.ritech.quizkarlo.Models.RankModel;
import com.ritech.quizkarlo.Models.TestModal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DbQuery {

    // Access a Cloud Firestore instance from your Activity
    public static FirebaseFirestore g_firestore;
    // List of Categories of tests(eg: History , polity)
    public static List<CategoryModel> g_catList = new ArrayList<>();
    // List of particular test data
    public static List<TestModal> g_testList = new ArrayList<>();
    // for selecte category
    public static int g_selected_cat_index = 0;
    // fetch test no.
    public static int g_selected_test_index = 0;

    // fetch particular test score for compare with user score , if user score more than add it


    // for bookmark
    public static List<String> g_bmIdList = new ArrayList<>();
    public static List<QuestionModel> g_bookmarksList = new ArrayList<>();

    // profile data fetch
    public static ProfileModel myProfile = new ProfileModel("NA", null,null, 0);
    // store all questions
    public static List<QuestionModel> g_quesList = new ArrayList<>();

    // for data store on firebase (rank or score save)
    public static RankModel myPerformance = new RankModel("NULL",0 , -1);

    //fetch total users
    public static  List<RankModel> g_usersList = new ArrayList<>();
    public static int g_usersCount = 0;
    public static boolean isMeOnTopList = false;

    // for the status of questions
    public static final int NOT_VISITED = 0;
    public static final int UNANSWERED = 1;
    public static final int ANSWERED = 2;
    public static final int REVIEW = 3;

    //
    static int tmp;



    // This is for User data store
    public static void createUserData(String email , String name, MyCompleteListener completeListener)
    {
        Map<String, Object> userData = new ArrayMap<>();

        userData.put("EMAIL_ID",email);
        userData.put("NAME",name);
        userData.put("TOTAL_SCORE",0);
        userData.put("BOOKMARKS",0);

        DocumentReference userDoc = g_firestore.collection("USERS").document(FirebaseAuth.getInstance().getCurrentUser().getUid());

        WriteBatch batch = g_firestore.batch();

        batch.set(userDoc,userData);

        DocumentReference countDoc = g_firestore.collection("USERS").document("TOTAL_USERS");
        batch.update(countDoc,"COUNT", FieldValue.increment(1));

        batch.commit()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        completeListener.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        completeListener.onFailure();
                    }
                });
    }

    //
    public static void saveProfileData (String name , String phone , MyCompleteListener completeListener)
    {
        Map<String,Object> profileData= new ArrayMap<>();

        profileData.put("NAME",name);

        if (phone != null)
            profileData.put("PHONE",phone);

        g_firestore.collection("USERS").document(FirebaseAuth.getInstance().getUid())
                .update(profileData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        myProfile.setName(name);

                        if (phone != null)
                            myProfile.setPhone(phone);

                        completeListener.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        completeListener.onFailure();
                    }
                });

    }

    // for fetch bookmarks from database
    public static void loadBookmarks(final MyCompleteListener completeListener)
    {
        g_bookmarksList.clear();
        tmp = 0;

        if (g_bmIdList.size() == 0)
        {
            completeListener.onSuccess();
        }


        for (int i=0; i < g_bmIdList.size(); i++)
        {
            String docID = g_bmIdList.get(i);

            g_firestore.collection("Questions").document(docID)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            if (documentSnapshot.exists())
                            {
                                g_bookmarksList.add(new QuestionModel(
                                   documentSnapshot.getId(),
                                   documentSnapshot.getString("QUESTION"),
                                   documentSnapshot.getString("A"),
                                   documentSnapshot.getString("B"),
                                   documentSnapshot.getString("C"),
                                   documentSnapshot.getString("D"),
                                   documentSnapshot.getLong("ANSWER").intValue(),
                                   0,
                                   -1,
                                        false
                                ));
                            }

                            tmp++;

                            if (tmp == g_bmIdList.size())
                            {
                                completeListener.onSuccess();
                            }

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            completeListener.onFailure();
                        }
                    });


        }

    }

    // user data fetch from firebase to show in side bar nav
    public static void getUserData(final MyCompleteListener completeListener)
    {
        g_firestore.collection("USERS").document(FirebaseAuth.getInstance().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        myProfile.setName(documentSnapshot.getString("NAME"));
                        myProfile.setEmail(documentSnapshot.getString("EMAIL_ID"));

                        if (documentSnapshot.getString("PHONE") != null)
                            myProfile.setPhone(documentSnapshot.getString("PHONE"));

                        if (documentSnapshot.getLong("BOOKMARKS") != null)
                            myProfile.setBookmarksCount(documentSnapshot.getLong("BOOKMARKS").intValue());


                        myPerformance.setScore(documentSnapshot.getLong("TOTAL_SCORE").intValue());
                        myPerformance.setName(documentSnapshot.getString("NAME"));

                        completeListener.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        completeListener.onFailure();
                    }
                });
    }

    // This is for questions data
    public static void loadCategories(final MyCompleteListener completeListener)
    {
        g_catList.clear();

        g_firestore.collection("QUIZ").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        Map<String, QueryDocumentSnapshot> docList = new ArrayMap<>();

                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots)
                        {
                            docList.put(doc.getId(),doc);
                        }

                        QueryDocumentSnapshot catListDoc = docList.get("Categories");

                        long catCount = catListDoc.getLong("COUNT");

                        for (int i=1 ; i<=catCount;i++)
                        {
                            String catID = catListDoc.getString("CAT"+ String.valueOf(i)+ "_ID");

                            QueryDocumentSnapshot catDoc = docList.get(catID);

                            int noOfTest = catDoc.getLong("NO_OF_TESTS").intValue();
                            String catName = catDoc.getString("NAME");

                            g_catList.add(new CategoryModel(catID,catName,noOfTest));
                        }

                        completeListener.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        completeListener.onFailure();
                    }
                });
    }

    // LOAD TEST ACTIVITY NO. OF TESTS
    public static void loadTestData(final MyCompleteListener completeListener)
    {
        g_testList.clear();

        g_firestore.collection("QUIZ").document(g_catList.get(g_selected_cat_index).getDocID())
                .collection("TESTS_LIST").document("TESTS_INFO")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        int noOfTests = g_catList.get(g_selected_cat_index).getNoOfTests();
                        for (int i=1; i<= noOfTests ; i++)
                        {
                            g_testList.add(new TestModal(
                                    documentSnapshot.getString("TEST" + String.valueOf(i)+ "_ID"),
                                    0,
                                    documentSnapshot.getLong("TEST" + String.valueOf(i)+ "_TIME").intValue()

                            ));
                        }

                        completeListener.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        completeListener.onFailure();
                    }
                });
    }

    // load questions from firebase
    public static void loadquestions(final MyCompleteListener completeListener)
    {
        g_quesList.clear();
        g_firestore.collection("Questions")
                .whereEqualTo("CATEGORY",g_catList.get(g_selected_cat_index).getDocID())
                .whereEqualTo("TEST",g_testList.get(g_selected_test_index).getTestID())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (DocumentSnapshot doc : queryDocumentSnapshots)
                        {
                            boolean isBookmarked = false;

                            if (g_bmIdList.contains(doc.getId()))
                                  isBookmarked = true;

                            g_quesList.add(new QuestionModel(
                                    doc.getId(),
                               doc.getString("QUESTION"),
                               doc.getString("A"),
                               doc.getString("B"),
                               doc.getString("C"),
                               doc.getString("D"),
                               doc.getLong("ANSWER").intValue(),
                                    -1,
                                    NOT_VISITED,
                                    isBookmarked
                            ));
                        }
                        completeListener.onSuccess();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {

                        completeListener.onFailure();
                    }
                });
    }


    public static void loadMyScores(MyCompleteListener completeListener)
    {
        g_firestore.collection("USERS").document(FirebaseAuth.getInstance().getUid())
                .collection("USER_DATA").document("MY_SCORES")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        for (int i=0; i<g_testList.size(); i++)
                        {
                            int top=0;
                            if (documentSnapshot.getLong(g_testList.get(i).getTestID()) != null)
                            {
                                top= documentSnapshot.getLong(g_testList.get(i).getTestID()).intValue();
                                Log.d("itsapp", String.valueOf(top));
                            }

                            g_testList.get(i).setTopScore(top);
                        }

                        completeListener.onSuccess();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        completeListener.onFailure();
                    }
                });
    }



    public static void saveResult(int score, final MyCompleteListener completeListener)
    {
        WriteBatch batch = g_firestore.batch();

        // Bookmarks upload in database
        Map<String , Object> bmData = new ArrayMap<>();

        for (int i=0; i < g_bmIdList.size(); i++)
        {
            bmData.put("BM" + String.valueOf(i+1) + "_ID" , g_bmIdList.get(i));
        }

        DocumentReference bmDoc = g_firestore.collection("USERS").document(FirebaseAuth.getInstance().getUid())
                .collection("USER_DATA").document("BOOKMARKS");

        batch.set(bmDoc, bmData);




        if (score > g_testList.get(g_selected_test_index).getTopScore()){
            DocumentReference a= g_firestore.collection("USERS").document(FirebaseAuth.getInstance().getUid());
            int total = (myPerformance.getScore() + score - g_testList.get(g_selected_test_index).getTopScore() ) ;
            Map<String , Object> userData = new ArrayMap<>();
            userData.put("TOTAL_SCORE", + total);
            batch.update(a, userData);


            myPerformance.setScore(total);



        }

        DocumentReference userDoc= g_firestore.collection("USERS").document(FirebaseAuth.getInstance().getUid());
        Map<String , Object> userData = new ArrayMap<>();
//        userData.put("TOTAL_SCORE",score);
        userData.put("BOOKMARKS", g_bmIdList.size());

        batch.update(userDoc, userData);

        if (score > g_testList.get(g_selected_test_index).getTopScore())
        {
            DocumentReference scoreDoc = userDoc.collection("USER_DATA").document("MY_SCORES");

            Map<String,Object> testData = new ArrayMap<>();
            testData.put(g_testList.get(g_selected_test_index).getTestID(),score);

            batch.set(scoreDoc,testData, SetOptions.merge());

        }

        batch.commit()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        if (score > g_testList.get(g_selected_test_index).getTopScore())
                        {
                            g_testList.get(g_selected_test_index).setTopScore(score);}

                       // myPerformance.setScore(score);

                        completeListener.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        completeListener.onFailure();
                    }
                });

    }

    // for load data for side nav bar
    public static void loadData(MyCompleteListener completeListener)
    {
        loadCategories(new MyCompleteListener() {
            @Override
            public void onSuccess() {

                getUserData(new MyCompleteListener() {
                    @Override
                    public void onSuccess() {

                        getUsersCount(new MyCompleteListener() {
                            @Override
                            public void onSuccess() {

                                loadBmIds(completeListener);
                            }

                            @Override
                            public void onFailure() {

                                completeListener.onFailure();
                            }
                        });
                    }

                    @Override
                    public void onFailure() {

                        completeListener.onFailure();
                    }
                });
            }

            @Override
            public void onFailure() {

                completeListener.onFailure();
            }
        });
    }


    // fetch  users rank list
    public static void getTopUsers(final MyCompleteListener completeListener)
    {
        g_usersList.clear();

        String myUID = FirebaseAuth.getInstance().getUid();

        g_firestore.collection("USERS")
                .whereGreaterThan("TOTAL_SCORE",0)
                .orderBy("TOTAL_SCORE", Query.Direction.DESCENDING)
                .limit(20)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        int rank = 1;
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots)
                        {
                            g_usersList.add(new RankModel(
                                    doc.getString("NAME"),
                                    doc.getLong("TOTAL_SCORE").intValue(),
                                    rank

                            ));

                            if (myUID.compareTo(doc.getId()) == 0)
                            {
                                isMeOnTopList = true;
                                myPerformance.setRank(rank);
                            }

                            rank++;

                        }

                        completeListener.onSuccess();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        completeListener.onFailure();
                    }
                });
    }


    // for total users
    public static  void getUsersCount(final MyCompleteListener completeListener)
    {
        g_firestore.collection("USERS").document("TOTAL_USERS")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        g_usersCount = documentSnapshot.getLong("COUNT").intValue();

                        completeListener.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        completeListener.onFailure();
                    }
                });
    }


    public static void loadBmIds(final MyCompleteListener completeListener)
    {
        g_bmIdList.clear();

        g_firestore.collection("USERS").document(FirebaseAuth.getInstance().getUid())
                .collection("USER_DATA").document("BOOKMARKS")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        int count = myProfile.getBookmarksCount();

                        for (int i=0 ; i < count; i++)
                        {
                            String bmID = documentSnapshot.getString("BM" + String.valueOf(i+1) + "_ID");
                            g_bmIdList.add(bmID);
                        }

                        completeListener.onSuccess();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        completeListener.onFailure();
                    }
                });
    }


}
