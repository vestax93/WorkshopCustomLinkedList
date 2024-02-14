import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class DoublyLinkedList {
    private class ListNode{
        private int item; //vrednost koju ce da sadrzi cvor
        private ListNode next; //cvorov sledbenik
        private ListNode previous; //cvorov prethodnik

        public ListNode(int item) { //konnstruktor za cvor
            this.item = item; //setujemo vrednost za taj cvor koji se pravi
        }
    }

    private ListNode head; //cvor koji oznacava glavu
    private ListNode tail; //cvor koji oznacava rep
    private int size; //velicina liste

    public void addFirst(int element){ //dodavanje na pocetak liste, dodaje se nova GLAVA i prima vrednost iz parametra element
        ListNode newHead = new ListNode(element); //napravili novi cvor koji nosi vrednost elementa, jos uvek nema defininsane prethodnika i sledbenika tj. oni su null
        //postoje dva slucaja: lista je prazna ili vec imamo neki element(e) u listi
        if(this.size == 0){ //u slucaju da je lista prazna
            this.head = this.tail = newHead; //ubacili smo cvor i rekli da je on ujedno i galva i rep jer je i jedini
        } else {
            newHead.next = this.head; //za novu glavu kazemo da joj je sledbenik (stara) glava
            this.head.previous = newHead; //za staru glavu (koja sada postaje drugi clan) prethodnik vise nije null nego je nova glava
            this.head = newHead; //za glavu tj. pocetak proglasavamo novi cvor tj. novu glavu
        }
        this.size++; //povecavamo broj cvorova u listi
    }

    public void addLast(int element){ //dodavanje elementa na KRAJ list tj. on postaje novi REP
        ListNode newTail = new ListNode(element); //inicijalizujemo element
        if(this.size == 0){ //ako je lista bila prazna
            this.tail = this.head = newTail; //novi rep je ujedno i glava i rep jer je jedini element
        } else {
            this.tail.next = newTail; //sledbenik starog repa je novi rep
            //this.tail = newTail; BITAN JE REDOSLED OVDE CVOR POKAZUJE SAM NA SEBE
            newTail.previous = this.tail; //prethodnik novog repa je stari rep (pretposlednji element sada)
            this.tail = newTail; //proglasavmo novi rep za zvanican rep tj. kraj cele liste
        }
        this.size++; //povecavamo broj elemenata u listi
    }

    public void forEach(Consumer<Integer> consumer){ //koristimo Consumer
        ListNode currentNode = this.head; //pocinje se od glave lsite
        while(currentNode != null){ //dok cvor ne postane NULL (sto znaci da smo stigli do kraja) petalja se vrti
            consumer.accept(currentNode.item); //accept prihavta ono sto je prosledjeno consumer-u i radi sa njegovom vrednoscu tj. item
            currentNode = currentNode.next; //pomeramo cvor na sledeci
        }
    }

    public int get(int index){
        checkIndex(index);

        if(index <= this.size/2){ //ako je indeks u prvoj polovini liste, krecemo od glave
            ListNode currentNode = this.head; //pocetak nam je glava

            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next; //pomeramo currentNode onoliko puta koliko na mje zadatao indeksom
            }
            return currentNode.item;// vratimo vrednost elementa na tom indeksu do koga smo stigli kad se petlja zavrsila
        } else { //indeks u drugoj polovini liste i krecemo od repa
            ListNode currentNode = this.tail;

            for (int j = this.size-1; j > index ; j--) { //krecemo od REPa i smanjujemo do indeksa
                currentNode = currentNode.previous; //trazimo prethodnike i current postaje svoj prethodnik
            }
            return currentNode.item; //vraca vrednost sa pozicije index-a
        }
    }

    public void checkIndex(int index){
        if(index < 0 || index >= this.size){//proveravamo da li je zadati indeks van okvira liste
            throw new IndexOutOfBoundsException("Ne posotji taj indeks");
        }
    }

    public int removeFirst(){
        checkSize(); //da li postoji bilo kakav element u listi
        int firstItem = this.head.item; //pamtimo vrednost na prvoj poziciji tj. glavi da bismo mogli da je vratimo
        this.head = this.head.next; //pomeramo glavu na sledeci element
        if(this.head == null){ //ako je glava sada null znaci da nije postojao sledeci element tj. da je onaj koji smo izbacili bio jedini
            this.tail = null; //podesimo da je i rep sada null jer je lista prazna
        } else { //ako glava nije null znaci da je bilo drugih elemenata
            this.head.previous = null; //kazemo da taj drugi element tj. nova glava vise ne pokazuje ni na sta tj. pokazuje na null
        }
        this.size--; //smanjujemo velicinu liste
        return firstItem; //vracamo vrednost koja je bila na prvoj poziciji
    }

    public int removeLast(){
        checkSize(); //provervamo da li postoji element u lsiti

        int lastItem = this.tail.item; //pamtimo vrednost na poslednjoj poziciji tj. repu
        this.tail = this.tail.previous; //proglasamo pretposlendji element za rep, ako je null znaci da je bio samo jedan elemeent u listi i to ovaj koji smo obrisali
        if(this.tail == null){ //ako je rep null znaci da nije bilo prethodnika kod starog repa
            this.head = null; //lista je prazna i glava i rep pokazuju na null
        } else {
            this.tail.next = null;//novi rep nema vise sledbenika
        }
        this.size--;//smanjujemo velicinu liste
        return  lastItem; //vracamo vrednost uzetu iz poslednjeg elementa
    }

    private void checkSize() {
        if(this.size == 0){ //ako je prazna lista bacamo Exception
            throw new NoSuchElementException("Ne postoji lista, ne mozemo nista da izbacimo");
        }
    }

    public int[] toArray(){
        int[] arr = new int[this.size];//inicijalizujemo niz velicine liste
        ListNode currentNode = this.head; //postavljamo jedan cvor na glavu (ne smemo da koristimo glavu direktno inace bismo izgubili informacije o glavi kada bi je sledeca metoda pozvala)
        int counter = 0; //counter za indekse u nizu
        while(currentNode != null){ //dok ne dodjemo do kraja liste
            arr[counter++] = currentNode.item; //dodajemo vrednost u niz i povecavamo counter
            currentNode = currentNode.next; //pomeramo se na sledeci cvor
        }
        return arr;//vracamo popunjen niz
    }


}
