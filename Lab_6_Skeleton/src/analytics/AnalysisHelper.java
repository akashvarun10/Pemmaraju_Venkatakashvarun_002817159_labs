/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analytics;

/**
 *
 * @author akashvarun
 */

import data.DataStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import model.Comment;
import model.Post;
import model.User;


public class AnalysisHelper {
    //Find Average number of likes per comment.
    //TODO
    public void getAverageLikesPerComments() {
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        int likeNumber = 0;
        int commentNumber = comments.size();
        for (Comment c : comments.values()) {
            likeNumber += c.getLikes();
        }
        
        System.out.println("Average number of likes per comments: " + likeNumber / commentNumber);
            
    }
    
    
    public void getPostWithMostComments() {
    DataStore data = DataStore.getInstance();
    Post postWithMostComments=null;
    for(Post p : data.getPosts().values()){
        if(postWithMostComments==null){
        postWithMostComments=p;
        }
        if(p.getComments().size()>postWithMostComments.getComments().size()){
        postWithMostComments=p;
        }
        
    }System.out.println("Q3-post with most comments "+ postWithMostComments.getPostId());
    }
    
    public void getPassiveUsers(){
        DataStore data=DataStore.getInstance();
        HashMap<Integer,Integer> postNumbers = new HashMap <Integer,Integer>();
        for (Post p : data.getPosts().values()){
            int userId =p.getUserId();
            if (postNumbers.containsKey(userId)){
                postNumbers.put(userId, postNumbers.get(userId)+1);
            } else {
                postNumbers.put( userId,1);
            }
       
        }
        ArrayList<User> users = new ArrayList(data.getUsers().values());
        Collections.sort(users,new UserMapComparator(postNumbers));
        System.out.println("Q4-The following users have the least posts:");
        for (int i =0;i<5;i++){
        System.out.println(users.get(i)+",-Post count"+postNumbers.get(users.get(i).getId()));
        }
    }
    
    public void getPassiveCommentUsers(){
        DataStore data=DataStore.getInstance();
        HashMap<Integer,Integer> commentNumber = new HashMap <Integer,Integer>();
        for (Comment c : data.getComments().values()){
            int userId =c.getUserId();
            if (commentNumber.containsKey(userId)){
                commentNumber.put(userId, commentNumber.get(userId)+1);
            } else {
               commentNumber.put( userId,1);
            }
       
        }
        ArrayList<User> users = new ArrayList(data.getUsers().values());
        Collections.sort(users,new UserMapComparator(commentNumber ));
        System.out.println("Q5-The following users have the least comments:");
        for (int i =0;i<5;i++){
        System.out.println(users.get(i)+",-Post count"+commentNumber.get(users.get(i).getId()));
        }
    }
    
    public void getPassiveAndActiveOverallUsers(){
        
        DataStore data=DataStore.getInstance();
        HashMap<Integer,Integer> overallNumber = new HashMap <Integer,Integer>();
        for (Comment c : data.getComments().values()){
            int userId =c.getUserId();
            if (overallNumber.containsKey(userId)){
                overallNumber.put(userId, overallNumber.get(userId)+1+c.getLikes());
            } else {
               overallNumber.put( userId,1+c.getLikes());
            }
       
        }
        for (Post p : data.getPosts().values()){
            int userId =p.getUserId();
            if (overallNumber.containsKey(userId)){
                overallNumber.put(userId, overallNumber.get(userId)+1);
            } else {
               overallNumber.put( userId,1);
            }
        ArrayList<User> users = new ArrayList(data.getUsers().values());
        Collections.sort(users,new UserMapComparator(overallNumber ));
        
        System.out.println("Q6-The following users have overall been passive:");
        for (int i =0;i<5;i++){
        System.out.println(users.get(i)+",-Overall count"+overallNumber.get(users.get(i).getId()));
        }
        //Collections.sort(users,new UserMapComparator (overallNumber));
        System.out.println("Q7 -The following users have overall been active: ");
        for (int i = users.size()-1;i>users.size()-6;i--){
            System.out.println(users.get(i)+ ", -Overall count : "+ overallNumber.get(users.get(i).getId()));   
        }}}}
