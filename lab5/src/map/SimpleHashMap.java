package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K,V>[] table;
	private double load_threshold = 0.75;
	private int capacity;
	private int size;
	
	/** Constructs an empty hashmap with the default initial capacity (16)
	and the default load factor (0.75). */
	@SuppressWarnings("unchecked")
	public SimpleHashMap(){
		capacity = 16;
		table = (Entry<K,V>[]) new Entry[capacity];
		size = 0;
	}
	
	/** Constructs an empty hashmap with the specified initial capacity
	and the default load factor (0.75). */
	public SimpleHashMap(int capacity){
		this.capacity = capacity;
		table = (Entry<K,V>[]) new Entry[capacity];
		size = 0;
	}
	
	public String show(){
		String str = "";
		for (int i=0; i < table.length; i++){
			str += (i + " ");
			Entry<K,V> temp = table[i];
			while (temp != null){
				str += temp.toString() + "\t";
				temp = temp.next;
			}
			str += "\n";
		}
		return str;
	}
	
	@Override
	public V get(Object key) {
		K keyObj = (K) key;
		Entry<K, V> e = find(index(keyObj), keyObj);
		
		if (e != null){
			return e.getValue();
		} else{
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K key, V val) {
		Entry<K,V> e = find(index(key), key);
		
		if (e != null){
			return e.setValue(val);
		}
		
		e = new Entry<K,V>(key, val);
		
		if(table[index(key)] != null) {
			e.next = table[index(key)];
		}
		
		table[index(key)] = e;
		size++;
		if ((double) size / capacity > load_threshold){
			rehash();
		}
		
		return null;
	}
	
	private void rehash(){
		capacity = capacity*2 + 1;
		Entry<K,V>[] preTable = table;
		table = (Entry<K,V>[]) new Entry[capacity];
		size = 0;
		
		for (Entry<K,V> e : preTable){
			while (e != null){
				put(e.getKey(), e.getValue());
				e = e.next;
			}
			
		}
	}

	@Override
	public V remove(Object key) {
		Entry<K,V> e = table[index((K) key)];
		if (e != null){
			if (e.getKey().equals((K) key)){
				table[index((K) key)] = e.next;
				size--;
				return e.getValue();
			}
			else{
				while (e.next != null) {
					if (e.next.getKey().equals(key)) {
						V v = e.next.getValue();
						e.next = e.next.next;
						size--;
						return v;
					}

					e = e.next;
				}
			}
		} 

		return null;
	}

	@Override
	public int size() {
		return size;
	}
	
	private int index(K key){
		return Math.abs(key.hashCode() % capacity);
	}
	
	private Entry<K,V> find(int index, K key){
		Entry<K,V> e = table[index];
		while (e != null){
			if (e.getKey().equals(key)){
				return e;
			}
			e = e.next;
		}
		return null;
	}
	
	
	public static class Entry<K, V> implements Map.Entry<K,V>{
		K key;
		V val;
		private Entry<K,V> next;
		
		public Entry(K key,V val){
			this.key = key;
			this.val = val;
		}
		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return val;
		}

		@Override
		public V setValue(V value) {
			// TODO Auto-generated method stub
			V oldVal = this.val;
			this.val = value;
			return oldVal;
		}
		
		public String toString(){
			return key.toString() + " = " + val.toString();
		}

	}


	public static void main(String[] args){
		int capacity = 3;
		SimpleHashMap<String, Integer> shm = new SimpleHashMap<String, Integer>(capacity);
		shm.put("Andreas", 10);
		shm.put("Alex", 10);
		shm.put("Ulrik", 12);
		shm.put("bla", -3);

		System.out.println(shm.show());
	}


}

