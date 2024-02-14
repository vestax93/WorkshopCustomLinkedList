public class Main {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.addFirst(5);
        list.addFirst(6);
        list.addLast(9);
        list.addLast(115);

        //list.forEach(t -> System.out.print(t + " "));
        //System.out.println();
        //System.out.println(list.get(0));
        //System.out.println();
        //System.out.println(list.removeFirst());
        //System.out.println(list.removeLast());
        int[] arr = list.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
