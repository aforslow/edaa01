package phonebook;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PhoneBook implements Serializable{
	private Map<String, Set<String>> phoneBook;
	private	static final long serialVersionUID = 1L;
	
	public PhoneBook() {
		phoneBook = new HashMap<String, Set<String>>();
	}
	
	/** 
	 * Associates the specified number with the specified 
	 * name in this phone book. 
	 * post: If the specified name is not present in this phone book,
	 *        the specified name is added and associated  with
	 *  	  the specified number. Otherwise the specified 
	 *  	  number is added to the set of number associated with name.
	 * @param name The name for which a phone number is to be added
	 * @param number The number associated with the specified name
	 * @return true if the specified name and number was inserted
	 */
	public boolean put(String name, String number) {
		if (!phoneBook.containsKey(name)){
			Set<String> numberList = new HashSet<String>();
			numberList.add(number);
			phoneBook.put(name, numberList);
		} else{
			Set<String> numbers = phoneBook.get(name);
			if (numbers.contains(number)) {
				return false;
			} else{
				numbers.add(number);
			}
			
		}
		return true;
	}
	
	
	/**
	 * Removes the the specified name from this phone book.
	 * post: If the specified name is present in this phone book,
	 * 		 it is removed. Otherwise this phone book is
	 * 		 unchanged.
	 * @param name The name to be removed
	 * @return true if the specified name was present
	 */
	public boolean remove(String name) {
		if (phoneBook.containsKey(name)){
			phoneBook.remove(name);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the specified number from name in this phone book.
	 * post: If the specified name and number is present in this phone book,
	 * 		 the number is removed. Otherwise this phone book is
	 * 		 unchanged.
	 * @param number The number to be removed
	 * @param name The name from which to remove the number
	 * @return true if the specified name and number was present
	 */
	public boolean removeNumber(String name, String number) {
		if (phoneBook.containsKey(name)){
			Set<String> numberlst = phoneBook.get(name);
			if (numberlst.contains(number)){
				numberlst.remove(number);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Retrieves a set of phone numbers for the specified name. If the 
	 * specified name is not present in this phone book an empty set is 
	 * returned.
	 * @param name The name whose associated phone numbers are to be returned  
	 * @return The phone numbers associated with the specified name
	 */
	public Set<String> findNumber(String name) {
			if (phoneBook.containsKey(name)){
					return new HashSet<String>(phoneBook.get(name));
			}
		return new HashSet<String>();
	}
	
	/**
	 * Retrieves a set of names associated with the specified phone number. 
	 * If the specified number is not present in this phone book an empty 
	 * set is returned.
	 * @param number The number for which the set of associated
	 * names is to be returned.
	 * @return The names associated with the specified number
	 */
	public Set<String> findNames(String number) {
		Set<String> persons = new HashSet<String>();
		Set<String> names = names();
		Iterator<String> itr = names.iterator();
		
		while (itr.hasNext()){
			String thisName = new String();
			thisName = itr.next();
			Set<String> nmr = findNumber(thisName);
			if (nmr != null && nmr.contains(number)){
				persons.add(thisName);
			}
		}
		return persons;
	}
	
	/**
	 * Retrieves the set of all names present in this phone book.
	 * The set's iterator will return the names in ascending order
	 * @return The set of all names present in this phone book
	 */
	public Set<String> names() {
		Set<String> names = phoneBook.keySet();
		return names;
	}
	
	/**
	 * Returns true if this phone book is empty.
	 * @return true if this phone book is empty
	 */	
	public boolean isEmpty() {
		return phoneBook.size() == 0;
	}
	
	/**
	 * Returns the number of names in this phone book.
	 * @return The number of names in this phone book
	 */
	public int size() {
		return phoneBook.size();
	}
	
	/**
	 * Returns a string containing all names and numbers (one entry per row) in the the phonebook.
	 * @return A string containing all names and numbers (one entry per row) in the the phonebook
	 */
	@Override
	public
	String toString(){
		Iterator<String> itr = names().iterator();
		String str = "";
		int i = 1;
		
		while (itr.hasNext()){
			String thisName = itr.next();
			str += i + ". " + thisName + "\n";
			Set<String> nList = findNumber(thisName);
			Iterator<String> itr2 = nList.iterator();
			
			while (itr2.hasNext()){
				String thisNumber = itr2.next();
				str += "    " + thisNumber + "\n";
			}
			i += 1;
			str += "\n";
		}
		return str;
	}

}
