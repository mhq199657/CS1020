/*
Name:
Matric Number:
*/

import java.util.*;

public class FileManager {
	boolean debug = false;
	Vector<Folder> _folderList = null;
	FileManager(){
		_folderList = new Vector<Folder>();
	}
	private void createFile(String fileName, int fileSize, String folderName){
		File newFile = new File(fileName,fileSize,folderName);
		Folder currentFolder = getFolder(folderName);
		currentFolder.addFile(newFile);
		if(debug){
			debugPrint();
		}
	}
	private void deleteFile(String fileName){
		for(int i = 0 ; i<_folderList.size(); i++){
			Folder currentFolder = _folderList.get(i);
			if(currentFolder.hasFile(fileName)){
				File currentFile = currentFolder.getFile(fileName);
				currentFolder.removeFile(currentFile);
				currentFile = null;
			}
		}
		if(debug){
			debugPrint();
		}
	}
	private void addFolder(String folderName){
		Folder newFolder = new Folder(folderName);
		_folderList.add(newFolder);
		if(debug){
			//System.out.print(_folderList);
			debugPrint();
		}
	}
	private Folder getFolder(String folderName){
		for(int i = 0; i<_folderList.size(); i++){
			Folder currentFolder = _folderList.get(i);
			if(currentFolder.getName().equals(folderName)){
				return currentFolder;
			}
		}
		return new Folder();
	}
	private Folder getFolder(File file){
		String folderName = file.getFolder();
		return getFolder(folderName);
	}
	private void moveFile(String fileName, String folderName){
		File currentFile= null;
		Folder currentFolder = null;
		for(int i = 0; i<_folderList.size(); i++){
			currentFolder = _folderList.get(i);
			if(currentFolder.hasFile(fileName)){
				currentFile = currentFolder.getFile(fileName);
				break;
			}
		}
		Folder destination = getFolder(folderName);
		currentFolder.moveFile(currentFile,destination);
		if(debug){
			debugPrint();
		}
	}
	public int count(String folderName){
		Folder currentFolder = getFolder(folderName);
		return currentFolder.count();
	}
	public String findLargest(){
		int maxSize = -1;
		Folder folderOfMaxSize = new Folder();
		for(int i = 0; i<_folderList.size(); i++){
			Folder currentFolder = _folderList.get(i);
			int currentSize = currentFolder.count();
			if(currentSize>maxSize){
				maxSize = currentSize;
				folderOfMaxSize = currentFolder;
			}
		}
		return folderOfMaxSize.getName();
	}
	private void debugPrint(){
		for(int i = 0; i<_folderList.size(); i++){
			System.out.print(_folderList.get(i).getName()+" ");
		}
		System.out.println();
		for(int i = 0; i<_folderList.size(); i++){
			Folder currentFolder = _folderList.get(i);
			for(int j=0; j<currentFolder._listOfFiles.size();j++){
				System.out.println(currentFolder._listOfFiles.get(j).getName());
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		FileManager fm = new FileManager();
		int num_of_queries = sc.nextInt();
		while(num_of_queries>0){
			switch(sc.next()){
				case "Createfolder":{
					fm.addFolder(sc.next());
					sc.nextLine();
					num_of_queries--;
					break;
				}
				case "Createfile":{
					String fileName = sc.next();
					int fileSize = sc.nextInt();
					String folderName = sc.next();
					fm.createFile(fileName, fileSize, folderName);
					sc.nextLine();
					num_of_queries--;
					break;
				}
				case "Deletefile":{
					String fileName = sc.next();
					fm.deleteFile(fileName);
					sc.nextLine();
					num_of_queries--;
					break;
				}
				case "Count":{
					String folderName = sc.next();
					System.out.println(fm.count(folderName));
					sc.nextLine();
					num_of_queries--;
					break;
				}
				case "Findlargest":{
					System.out.println(fm.findLargest());
					num_of_queries--;
					break;
				}
				case "Movefile":{
					String fileName = sc.next();
					String folderName = sc.next();
					fm.moveFile(fileName, folderName);
					num_of_queries--;
					break;
				}
			}
		}

	}
}

class File {
	//define the appropriate attributes and constructor
	String _name;
	int _size;
	String _folderName;
	File(){
		this("Invalid",0,"Invalid");
		//System.out.print("Invalid access to file.");
	}
	File(String name, int size, String folderName){
		_name = name;
		_size = size;
		_folderName = folderName;

	}
	public String getName(){
		return _name;
	}
	public String getFolder(){
		return _folderName;
	}
	public int getSize(){
		return _size;
	}
	public void move(Folder destination){
		_folderName = destination.getName();
	}

}

class Folder {
	//define the appropriate attributes and constructor
	String _name;
	Vector<File> _listOfFiles = null;
	Folder(){
		this("Invalid");
	}
	Folder(String name){
		_name = name;
		_listOfFiles = new Vector<File>();
	}
	public String getName(){
		return _name;
	}
	public void addFile(File fileName){
		_listOfFiles.add(fileName);
	}
	public void removeFile(File fileName){
		_listOfFiles.remove(fileName);
	}
	public boolean hasFile(String fileName){
		for(int i = 0; i<_listOfFiles.size();i++){
			File currentFile = _listOfFiles.get(i);
			if(currentFile.getName().equals(fileName)){
				return true;
			}
		}
		return false;
	}
	public File getFile(String fileName){
		for(int i = 0; i<_listOfFiles.size();i++){
			File currentFile = _listOfFiles.get(i);
			if(currentFile.getName().equals(fileName)){
				return currentFile;
			}
		}
		return new File();
	}
	public void moveFile(File file, Folder destination){
		file.move(destination);
		_listOfFiles.remove(file);
		destination.addFile(file); 
	}
	public int count(){
		int totalSize = 0;
		for(int i = 0; i<_listOfFiles.size(); i++){
			totalSize += _listOfFiles.get(i).getSize();
		}
		return totalSize;
	}
}
