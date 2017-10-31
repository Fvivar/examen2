package gt.edu.url.examen2.problema3;



public class LinkedPositionalList implements PositionalList<E> {
	private static class Node<E> implements Position<E>{
		private E element;
		private Node<E> prev;// Anterior
		private Node<E> next;// Siguiente
		
		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}
		
		public E getElement() throws IllegalStateException {
			if (next == null) // Nodo no valido
				throw new IllegalStateException("Posicion invalida");
			return element;
		}
		
		public void setElement(E e) {
			element = e;
		}

		public Node<E> getPrev() {
			return prev;
		}

		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}
	
	
	private Node<E> header = null;// Referencia
	private Node<E> trailer = null;
	private int size = 0;
	
	public LinkedPositionalList(){
        size = 0;
        header = new Node(null, null, null);
        trailer = new Node(null, header, null);
        header.setNext(trailer);
    }
	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
 		if (!(p instanceof Node))
 			throw new IllegalArgumentException("P invalido");
 		Node<E> node = (Node<E>) p; // safe cast
 		if (node.getNext() == null)
 			throw new IllegalArgumentException("p ya no se encuentra en la lista");
 		return node;
 	}
	private Position<E> position(Node<E> node) {
 		if (node == header || node == trailer)
 			return null; // do not expose user to the sentinels
 		return node;
 	}
	@Override
	public int size() {return size;}
@Override
	public boolean isEmpty() {return size == 0;}
@Override
	public Position<E> first( ) {
		return position(header.getNext());
	}
@Override
	public Position<E> last( ) {
		return position(trailer.getPrev());
	}

	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getPrev());
	}

	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		return position(node.getNext());
	}
	
	
	private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
		Node<E> newest = new Node<>(e, pred, succ);
		succ.setPrev(newest);
		pred.setNext(newest);
		size++;
		return newest;
	}
	@Override
	public Position<E> addFirst(E e) {
		return addBetween(e, header, header.getNext());
	}
	@Override
	public Position<E> addLast(E e) {
		return addBetween(e, trailer.getPrev(), trailer);
	}
	
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException{
		Node<E> node = validate(p);
		return addBetween(e, node.getPrev(), node);
	}
	
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException{ 
		Node<E> node = validate(p);
		return addBetween(e, node, node.getNext());
	}
	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> node = validate(p);
		E answer = node.getElement();
		node.setElement(e);
		return answer;
	}
	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		E answer = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		return answer;
	}
	@Override
	public void swap(Position<E> p,Position<E> q) {
		Node<E> aux = validate(p);
		Node<E> AuxPrevPosition= aux.getPrev();
		Node<E> AuxNextP = aux.getNext();
		
		Node<E> aux2=validate(q);
		Node<E> Aux2PrevPosition= aux2.getPrev();
		Node<E> Aux2NextP = aux2.getNext();
		
		AuxPrevPosition.setNext(aux2);
		AuxNextP.setPrev(aux2);
		
		Aux2PrevPosition.setNext(aux);
		Aux2NextP.setPrev(aux);
	}
	}
}
