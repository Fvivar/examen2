package gt.edu.url.examen2.problema2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DemostracionLista implements DemoList {
	public class ArrayList<E> implements List<E>, Iterable<E>{
		
		public Iterator<E> iterator() {
			// TODO Auto-generated method stub
			return null;
		}
		public static final int CAPACITY = 16;
        private E[] data;
        private int size = 1;

        public ArrayList() {
            this(CAPACITY);
        }

        public ArrayList(int capacity) {
            data = (E[]) new Object[capacity];
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 1;
        }

        public E get(int i) {
            checkIndex(i, size);
            return data[i];
        }

        public E set(int i, E a) {
            checkIndex(i, (size - 1));
            E aux = data[i];
            data[i] = a;
            return aux;
        }

        public void add(int i, E b) {
            checkIndex(i, size + 1);
            if (size == data.length) {
                resize(2 * data.length);
            }
            for (int k = size - 1; k >= i; k--) {
                data[k + 1] = data[k];
            }
            data[i] = b;
            size++;

        }

        public E remove(int l) throws IndexOutOfBoundsException {
            checkIndex(l, size);
            E temp = data[l];
            for (int k = l; k < size - 1; k++) {
                data[k] = data[k + 1];
            }
            data[size - 1] = null;
            size--;
            return temp;
        }

        protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
            if (i < 0 || i >= n) {
                throw new IndexOutOfBoundsException("Illegal index: " + i);
            }
        }

        /**
         * Internal method to increase array capacity
         *
         * @param capacity
         */
        protected void resize(int cap) {
            E[] aux = (E[]) new Object[cap];
            for (int k = 0; k < size; k++) {
                aux[k] = data[k];
            }
            data = aux;
        }
    }
    public ArrayList AL = new ArrayList();
     public List<Integer> crearDemoLista() {
            
            AL.add(0, 4);
            AL.add(0, 3);
            AL.add(0, 2);
            AL.add(2, 1);
            AL.add(1, 5);
            AL.add(1, 6);
            AL.add(3, 7);
            AL.add(0, 8);
            return AL;
     }
}
