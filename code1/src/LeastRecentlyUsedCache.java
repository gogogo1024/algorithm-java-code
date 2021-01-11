/**
 * 设计LRU缓存结构，该结构在构造时确定大小，假设大小为K，并有如下两个功能。
 * set(key,value):将记录(key,value)插入该结构。
 * get(key):返回key对应的value值。
 * 【要求】
 * 1.	set和get方法的时间复杂度为O(1)
 * 2.	某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的3.
 * 当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的
 * 【举例】
 * 假设缓存结构的实例是cache，大小为3，并依次发生如下行为1.cache.set("A",1)。最常使用的记录为("A",1)
 * 2.	cache.set("B",2)。最常使用的记录为("B",2)，("A",1)变为最不常使用的
 * 3.	cache.set("C",3)。最常使用的记录为("C",2)，("A",1)还是最不常使用的
 * 4.	cache.get("A")。最常使用的记录为("A",1)，("B",2)变为最不常使用的
 * 5.	cache.set("D",4)。大小超过了3，所以移除此时最不常使用的记录("B",2)，加入记录("D",4)，
 * 并且为最常使用的记录，然后("C",2)变为最不常使用的记录。
 */

import java.util.HashMap;

/**
 * @author huangcheng
 */
public class LeastRecentlyUsedCache {
  /**
   * 节点结构
   *
   * @param <K> 节点的key
   * @param <V> 节点的值
   */
  public static class Node<K, V> {
    public K key;
    public V value;
    public Node<K, V> last;
    public Node<K, V> next;

    public Node(K key, V value) {
      this.key = key;
      this.value = value;
    }


    /**
     * 双向链表 尾部为最新节点，头部为最旧节点
     *
     * @param <K> 节点的key
     * @param <V> 节点的值
     */
    public static class NodeDoubleLinkedList<K, V> {
      private Node<K, V> head;
      private Node<K, V> tail;

      public NodeDoubleLinkedList() {
        this.head = null;
        this.tail = null;
      }

      /**
       * 添加新节点 默认都是添加到尾部
       *
       * @param newNode
       */
      public void addNode(Node<K, V> newNode) {
        if (newNode == null) {
          return;
        }
        if (this.head == null) {
          this.head = newNode;
          this.tail = newNode;
        } else {
          this.tail.next = newNode;
          newNode.last = this.tail;
          this.tail = newNode;
        }
      }


      /**
       * 移动节点到尾部
       *
       * @param node 节点
       */
      public void moveNodeToTail(Node<K, V> node) {
        if (this.tail == node) {
          return;
        }
        if (this.head == node) {
          this.head = node.next;
          this.head.last = null;
        } else {
          node.last.next = node.next;
          node.next.last = node.last;
        }
        node.last = this.tail;
        node.next = null;
        this.tail.next = node;
        this.tail = node;
      }

      /**
       * 移除头节点 （当节点数量超过双向链表的容量时候，应为头节点就是用的最少的）
       *
       * @return 返回头节点
       */
      public Node<K, V> removeHead() {
        if (this.head == null) {
          return null;
        }
        // 这里用res是为了保存之前的head节点
        Node<K, V> res = this.head;
        if (this.head == this.tail) {
          this.head = null;
          this.tail = null;
        } else {
          this.head = res.next;
          res.next = null;
          this.head.last = null;
        }
        return res;
      }
    }

    public static class MyCache<K, V> {
      private HashMap<K, Node<K, V>> keyNodeMap;
      private Node.NodeDoubleLinkedList<K, V> nodeList;
      private int capacity;

      public MyCache(int capacity) {
        if (capacity < 1) {
          throw new RuntimeException("should be more than 0");
        }
        this.keyNodeMap = new HashMap<K, Node<K, V>>();
        this.nodeList = new NodeDoubleLinkedList<K, V>();
        this.capacity = capacity;
      }

      /**
       * 获取key的节点，并且把该节点移到尾部
       *
       * @param key 节点key
       * @return 节点
       */
      public V get(K key) {
        if (this.keyNodeMap.containsKey(key)) {
          Node<K, V> res = this.keyNodeMap.get(key);
          this.nodeList.moveNodeToTail(res);
          return res.value;
        }
        return null;
      }

      /**
       * 设置key的节点，并且把该节点移到尾部
       *
       * @param key 节点key
       * @return 节点
       */
      public V set(K key, V value) {
        if (this.keyNodeMap.containsKey(key)) {
          Node<K, V> res = this.keyNodeMap.get(key);
          res.value = value;
          this.nodeList.moveNodeToTail(res);
        } else {
          Node<K, V> newNode = new Node<K, V>(key, value);
          this.keyNodeMap.put(key, newNode);
          this.nodeList.addNode(newNode);
          if (this.keyNodeMap.size() == this.capacity + 1) {
            this.removeMostUnusedCache();
          }
        }
        return null;
      }

      private void removeMostUnusedCache() {
        Node<K, V> removeNode = this.nodeList.removeHead();
        this.keyNodeMap.remove(removeNode.key);
      }
    }

    public static void main(String[] args) {
      MyCache<String, Integer> cache = new MyCache<String, Integer>(3);
      cache.set("A", 1);
      cache.set("B", 2);
      cache.set("C", 3);
      System.out.println(cache.get("B"));
      System.out.println(cache.get("A"));
      cache.set("D", 4);
      System.out.println(cache.get("D"));
      System.out.println(cache.get("C"));
    }
  }

}
