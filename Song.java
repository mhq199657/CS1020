class Animal {
/* TODO: Implement data and functionality of an Animal here */
	private String _name;
	private String _song;
	Animal(String n, String s){
		_name = n;
		_song = s;
	}
	public String sing(){
		return _name+" goes "+_song;
	}
	//Provided by tutor
	public String getName(){
		return _name;
	}
	public String getSound(){
		return _sound;
	}
}
public class Song {
private Animal[] _animals;
private static final int ANIMAL_COUNT = 5;
public Song() {
	_animals = new Animal[ANIMAL_COUNT];
	_animals[0]= new Animal("Dog","woof");
	_animals[1]= new Animal("Cat","meow");
	_animals[2]= new Animal("Bird","tweet");
	_animals[3]= new Animal("Mouse","squeak");
	_animals[4]= new Animal("Cow","moo");
}
public void display() {
	for (int i = 0; i < ANIMAL_COUNT; i++)
		System.out.println( _animals[i].sing() );
}
public static void main(String[] args) {
		(new Song()).display();
	}
}