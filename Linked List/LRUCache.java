class Node {
    Node prev;
    Node next;
    int key;
    int value;

    Node(int key, int value){
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}

// refer STRIVER old videos for examples and expln.
// T: Average O(1) - get, put, insert, remove
// S: O(capacity) - map size and List size
/**
* Requirement -
* maintain the insertion order
* at the time of access (get call), the cache is updated
* a data structure to store key-value pairs => HashMap
* to insert and remove frequently, we need access to the nodes in O(1)
* hence, a Doubly Linked List
* head and tail pointers are mainted to ease the handling of 0 and 1 length cases. 
 */
class LRUCache {
    Map<Integer, Node> map;
    Node head; 
    Node tail;
    int cap;

    public LRUCache(int capacity) {
        map = new HashMap();
        cap = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            remove(node);
            insert(node);
            return node.value;
        }
        return -1; // not found case
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            remove(map.get(key)); // remove node from map
        }
        if(map.size() == cap){
            remove(tail.prev); // remove tail.prev as it is LRU 
        }
        insert(new Node(key, value));
    }

    private void insert(Node node){
        map.put(node.key, node);
        Node headNext = head.next;
        head.next = node;
        node.prev = head;
        headNext.prev = node;
        node.next = headNext;
    }

    private void remove(Node node){
        map.remove(node.key); // remove from map
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
