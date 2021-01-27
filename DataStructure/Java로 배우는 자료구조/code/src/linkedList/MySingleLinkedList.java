package linkedList;

public class MySingleLinkedList<T> {

    // 첫번째 node
    private Node<T> head;
    // 전체 node의 갯수
    private int size;

    // head는 null, size는 0으로 초기화
    public MySingleLinkedList() {
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

    // remove된 데이터를 return 해줄수 있다.
    public T removeFirst() {
        if (head == null)
            return null;
        T temp = head.data;
        head = head.next;
        size--;

        return temp;
    }

    public T removeAfter(Node<T> before) {
        // before의 다음노드가 존재하지 않는다면 null을 반환한다.
        if (before.next == null) {
            return null;
        }
        T temp = before.next.data;
        size--;
        return temp;
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

    // 해당 index 위치에 node를 삭제
    public void remove(int index) {

    }

    // index번째 node에 저장되어 있는 data를 반환한다.
    public T get(int index) {
        if (index < 0 || index >= size)
            return null;

        return getNode(index).data;
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

    // 검색하고자 하는 item의 index를 찾는다.
    public int indexOf(T item) {
        // head를 바로 사용하면 head의 주소값이 변경되므로 복사한다.
        Node<T> p = head;
        int index = 0;
        // p가 마지막 노드일때는 next가 null이 된다.
        while (p != null) {
            if (p.data.equals(item))
                return index;
            p = p.next;
            index++;
        }
        // item을 가진 node를 찾지 못했다면 -1일 반환한다.
        return -1;
    }

    public static void main(String[] args) {
        MySingleLinkedList<String> list = new MySingleLinkedList<>();
        list.add(0, "Saturday");
        list.add(1, "Friday");
        list.add(0, "Monday"); // M, S, F
        list.add(2, "Tuesday"); // M, S, T ,F

        String str = list.get(2); // str = "Tuesday";
        list.remove(2);  // M, S, F

        int pos = list.indexOf("Friday");  // pos = 2
    }
}
