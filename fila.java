package filaPrioridade;

public class fila<T> {

    private Node<T> front;
    private Node<T> rear;
    private int length;

    private static class Node<T> {
        private final T data;
        private Node<T> next;
        private final int priority;

        public Node(T data, int priority) {
            this.data = data;
            this.next = null;
            this.priority = priority;
        }
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void enqueue(T data, int priority) {

        Node<T> temp = new Node<>(data, priority);

        if (front == null) {
            rear = new Node<>(data, priority);
            front = rear;
            length++;
        }

        else if (front.priority < priority) {
            temp.next = front;
            front = temp;
            length++;
        }
        else if (rear.priority == priority) {
            rear.next = new Node<T>(data, priority);
            rear = rear.next;
            length++;
        }
        else {
            Node<T> aux = front;
            while (aux.next != null && aux.next.priority >= priority) {
                      aux = aux.next;
            }
            temp.next = aux.next;
            aux.next = temp;
            length++;
        }

    }

    public T dequeue() {
        T aux;
        if(!isEmpty()){
            aux = front.data;
            front = front.next;
        }else {
            return null;
        }
        length--;
        return aux;
    }

    public String[] showQueue() {
        int i = 0;
        Node<T> aux1 = front;
        String[] fila = new String[length];

        while (aux1 != null) {
            fila[i] = (String) aux1.data;
            aux1 = aux1.next;
            i++;
        }
        return fila;
    }
}