package project.pkg3;

public class LinearProbingHash<Key> {
    int[] collision = new int[3000];
    public Key[] table;
    int M;
    int N; // number of full elements
    boolean[] full;

    public LinearProbingHash(int M){
        table = (Key[]) new Object[M];
        full = new boolean[M];
        this.N=0;
        this.M = M;
    }

    public int hash(Key t){
        return ((t.hashCode()&0x7fffffff) % M);

    }

    public boolean insert(Key key){
        if (key == null){
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (N >= M){
            resize(2*M);
        }
        if(contains(key)){
            collision[hash(key)]++;
        }
        int i;
        int h = hash(key);
        //System.out.println("hash("+ key + ")= "+h);
        for (i = h; table[i] != null; i = (i + 1) % M) {
            if (table[i].equals(key)) {
                return false;
            }
            /*if(i+1 == h){
                break;
            }*/
        }
        table[i] = key;
        N++; // increase number of stored items
        return true;
    }

    // resizes the hash table to the given capacity by re-hashing all of the keys
    private void resize(int capacity) {
        System.out.println("resize");
        LinearProbingHash<Key> temp = new LinearProbingHash<Key>(capacity);
        for (int i = 0; i < M; i++) {
            if (table[i] != null) {
                temp.insert(table[i]);
            }
        }
        table = temp.table;
        full = temp.full;
        M    = temp.M;
    }

    public void delete(Key key) {
        if (key == null){
            throw new IllegalArgumentException("argument to delete() is null");
        }
        if (!contains(key)){
            return;
        }
        int i = hash(key);
        while (!key.equals(table[i])) {
            i = (i + 1) % M;
        }
        table[i] = null;
        i = (i + 1) % M;
        while (table[i] != null) { 
            Key keyToRehash = table[i];
            table[i] = null;
            N--;
            insert(keyToRehash);
            i = (i + 1) % M;
        }
        N--;
        if (N > 0 && N <= M/8){
            resize(M/2);
        }
    }

    public boolean contains(Key key) {
        if (key == null){
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    public Key get(Key key) {
        if (key == null){
            throw new IllegalArgumentException("argument to get() is null");
        }
        for (int i = hash(key); table[i] != null; i = (i + 1) % M){
            if (table[i].equals(key)){
                return table[i];
            }
        }
        return null;
    }
    
    public int geti(Key key) {
        if (key == null){
            throw new IllegalArgumentException("argument to get() is null");
        }
        for (int i = hash(key); table[i] != null; i = (i + 1) % M){
            if (table[i].equals(key)){
                return i;
            }
        }
        return -1;
    }

    public int distinctCount(){
        int distinctCount=0;
        for (int k=0;k<table.length;k++){
            if(table[k]!=null){
                distinctCount++;
            }
        }
        return distinctCount;
    }
    public String toString(){
        String s = "[";
        for(int i = 0; i < M; i++){
            s+=table[i]+",";
        }
        return s+"]";
    }
}
