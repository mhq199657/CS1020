import java.util.*;
import java.lang.*;
public class Forum {
	Vector<User> _userList;
	Vector<Thread> _threadList;
	//Constructors
	Forum(){
		_userList = new Vector<User>();
		_threadList = new Vector<Thread>();
	}
	Forum(Vector<String> userList, Vector<String> threadList){
		_userList = new Vector<User>();
		for(int i = 0 ; i<userList.size(); i++){
			User newUser = new User(userList.get(i));
			_userList.add(newUser);
		}
        _threadList = new Vector<Thread>();
		for(int i = 0; i<threadList.size(); i++){
            //System.out.print(i);
			Thread newThread = new Thread(threadList.get(i));
			_threadList.add(newThread);
		}
	}
	private Thread getThread(String threadName){
		Thread currentThread = null;
		for(int i = 0; i< _threadList.size(); i++){
			currentThread = _threadList.get(i);
            //System.out.print(_threadList.size());
			if(currentThread.getName().equals(threadName)){
				return currentThread;
			}
		}
		return null;
	}
	private User getUser(String userName){
		User currentUser = null;
		for(int i = 0; i< _userList.size(); i++){
			currentUser = _userList.get(i);
			if(currentUser.getName().equals(userName)){
				return currentUser;
			}
		}
		return null;
	}
	private void post(String threadName, String userName, String message){
		Thread currentThread = getThread(threadName);
		if(currentThread==null){
			System.out.println("no such thread");
			return;
		}
		User currentUser = getUser(userName);
		if(currentUser==null){
			System.out.println("no such user");
			return;
		}
		currentThread.post(currentUser, message);
	}
    private int count(String threadName){
        Thread currentThread = getThread(threadName);
        return currentThread.getPostNumber();
    }
    private int numpost(String userName){
        User currentUser = getUser(userName);
        if(currentUser==null){
            System.out.println("no such user");
            return -1;
        }
        return currentUser.getNumOfPost();
    }
    private String maxpost(String threadName){
        Thread currentThread = getThread(threadName);
        if(currentThread==null){
            return "no such thread";
        }
        return currentThread.maxpost(_userList);
    }
    private void content(String threadName, int currentPostNumber){
        Thread currentThread = getThread(threadName);
        if(currentThread==null){
            System.out.println( "no such thread");
            return;
        }
        System.out.println(currentThread.retrieve_content(currentPostNumber));

    }

	public static void main(String[] args) {
        //define your main method here
        Scanner sc = new Scanner(System.in);
        int num_of_users = sc.nextInt();
        Vector<String> userList = new Vector<String>(num_of_users);
        for(int i = 0; i<num_of_users; i++){
        	userList.add(sc.next());
            sc.nextLine();
        }
        int num_of_threads = sc.nextInt();
        Vector<String> threadList = new Vector<String>(num_of_threads);
        for(int i = 0; i<num_of_threads;i++){
        	threadList.add(sc.next());
            sc.nextLine();
        }
        Forum fr = new Forum(userList,threadList);
        int num_of_queries = sc.nextInt();
        while(num_of_queries>0){
        	switch(sc.next()){
        		case "post":{
        			String threadName = sc.next();
        			String userName = sc.next();
        			String message = sc.nextLine();
                    message = message.substring(1);
        			fr.post(threadName, userName, message);
        			num_of_queries--;
        			break;
        		}
                case "count":{
                    int num_to_be_counted = sc.nextInt();
                    int count = 0;
                    for(int i = 0; i<num_to_be_counted; i++){
                        String currentThread = sc.next();
                        count +=fr.count(currentThread);
                    }
                    System.out.println(count);
                    num_of_queries--;
                    break;
                }
                case "numpost":{
                    String currentUser = sc.next();
                    int num_of_posts = fr.numpost(currentUser);
                    if(num_of_posts == -1){
                        num_of_queries--;
                        break;
                    }
                    System.out.println(num_of_posts);
                    num_of_queries--;
                    break;
                }
                case "maxpost":{
                    String currentThread = sc.next();
                    System.out.println(fr.maxpost(currentThread));
                    num_of_queries--;
                    break;
                }
                case "content":{
                    String currentThread = sc.next();
                    int currentPostNumber = sc.nextInt();
                    fr.content(currentThread,currentPostNumber);
                    num_of_queries--;
                    break;
                }
        	}
        }
	}
}

class Thread {
    //define the appropriate attributes, constructor, and methods here
    String _threadName;
    Vector<Post> _postList;
    //Constructors
    Thread(String threadName){
    	_threadName = threadName;
    	_postList = new Vector<Post>();
    }
    //Accessors
    public String getName(){
    	return _threadName;
    }
    public int getPostNumber(){
    	return _postList.size();
    }
    public void post(User user, String message){
    	System.out.println(message);
    	Post newPost = new Post(this, user, message);
    	_postList.add(newPost);
    }
    public String maxpost(Vector<User> userList){
        int[] countArray = new int[userList.size()];
        for(int i = 0; i < _postList.size();i++){
            Post currentPost = _postList.get(i);
            User currentUser = currentPost.getUser();
            for(int j = 0; j<userList.size();j++){
                if(userList.get(j)==currentUser){
                    countArray[j]++;
                }
            }
        }
        int maxNumberOfPosts = 0;
        String maxUserName = "";
        for(int i = 0; i<userList.size(); i++){
            if(countArray[i]>maxNumberOfPosts){
                maxNumberOfPosts = countArray[i];
                maxUserName = userList.get(i).getName();
            }
            if(countArray[i]==maxNumberOfPosts){
                if(userList.get(i).getName().compareTo(maxUserName)<0){
                    maxUserName = userList.get(i).getName();
                }
            }   
        }
        return maxUserName;
    }
    public String retrieve_content(int k){
        if(k>_postList.size()){
            return "no such post";
        }else{
            return _postList.get(k-1).getMessage();
        }
    }
}

class Post {
    //define the appropriate attributes, constructor, and methods here
    int _postNumber;
    User _user;
    String _message;
    Thread _thread;
    Post(Thread thread, User user, String message){
    	_postNumber = thread.getPostNumber()+1;
    	_user = user;
    	_message= message;
    	_thread = thread;
        user.increNumOfPost();
    }
    public String getMessage(){
        return _message;
    }
    public User getUser(){
        return _user;
    }
}

class User {
    //define the appropriate attributes, constructor, and methods here
    String _userName;
    int _numOfPosts;
    User(String userName){
    	_userName = userName;
    	_numOfPosts = 0;
    }
    public String getName(){
    	return _userName;
    }
    public int getNumOfPost(){
        return _numOfPosts;
    }
    public void increNumOfPost(){
        _numOfPosts++;
    }
}
