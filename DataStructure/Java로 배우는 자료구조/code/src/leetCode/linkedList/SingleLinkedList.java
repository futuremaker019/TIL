package leetCode.linkedList;

public class SingleLinkedList<T> {

    public Node<T> head;
    public int size;

    public SingleLinkedList() {
        this.head = null;
        this.size = 0;
    }

    //
    public void addFirst(T item) {
        // 1.  데이터를 가지는 노드를 생성한다.
        Node<T> newNode = new Node<>(item);
        // 2. 새로운 노드의 next 필드에 head가 기리키는 node의 주소를 저장한다.
        newNode.next = head;
        // 3. 새로운 노드를 새로운 head로 한다.
        head = newNode;
        // 4. list의 size를 증가시킨다.
        size++;
    }

    public void addAfter(Node<T> before, T item) {
        Node<T> newNode = new Node<>(item);

        // before의 다음 노드의 주소값은 next에 저장되어있다.
        // 새로운 노드의 next 필드가 before의 다음 노드를 가리키도록한다.
        newNode.next = before.next;

        // 새로운 노드의 주소를 before의 next에 저장한다.
        before.next = newNode;
        size++;
    }

    // 해당 index 위치에 node를 추가
    public void add(int index, T item) {
        if (index < 0 || index > size)
            // exception을 throw해주어야 한다.
            return;

        if (index == 0)
            addFirst(item);
        else {
            Node<T> q = getNode(index - 1);
            addAfter(q, item);
        }
    }

    // index번째 node에 저장되어 있는 data를 반환한다.
    public T get(int index) {
        if (index < 0 || index >= size)
            return null;

        Node<T> p = head;
        for (int i = 0; i<index; i++)
            p = p.next;

        return p.data;
    }

    // index번째 node의 주소를 반환
    public Node<T> getNode(int index) {
        if (index < 0 || index >= size)
            return null;

        Node<T> p = head;
        for (int i = 0; i<index; i++)
            p = p.next;
        return p;
    }

    public static void main(String[] args) {

    }
}
