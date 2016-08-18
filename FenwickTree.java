import java.util.*;
public class FenwickTree {
    public long count = 0;
    /**
     * binaryIndexedTree start with index 1.
     */
    public void updateBinaryIndexedTree(int binaryIndexedTree[], int val, int index){
        while(index < binaryIndexedTree.length){
            binaryIndexedTree[index] += val;
	    count ++;
            index = getNext(index);
        }
    }
    public void topDownConstructBIT(int binaryIndexedTree[], int val, int index) {
	int n = 1;
	binaryIndexedTree[index] += val;
	this.count ++;
	int m = index & (-index);
	while (n < m) {
	    binaryIndexedTree[index] += binaryIndexedTree[index-n];
	    this.count ++;
	    n <<= 1;
	}
    }
		
    /**
     * Start from index+1 if you want prefix sum 0 to index. Keep adding value
     * till you reach 0
     */
    public int getSum(int binaryIndexedTree[], int index){
        index = index + 1;
        int sum = 0;
        while(index > 0){
            sum += binaryIndexedTree[index];
            index = getParent(index);
        }
        return sum;
    }
    
    /**
     * Creating tree is like updating Fenwick tree for every value in array
     */
    public void createTree(int input[], int binaryIndexedTree[]){
        this.count = 0;
	for(int i=1; i <= input.length; i++){
            updateBinaryIndexedTree(binaryIndexedTree, input[i-1], i);
        }
    }
    public void createTree2(int input[], int binaryIndexedTree[]) {
	this.count = 0;
	for (int i = 1; i <= input.length; i ++) {
	    topDownConstructBIT(binaryIndexedTree, input[i-1], i);
	}
    }
    
    private int getParent(int index){
	return index - (index & -index);
    }
    
    private int getNext(int index){
	return index + (index & -index);
    }
    
    public static void main(String args[]){
        Random random = new Random();
	int size = Integer.parseInt(args[0]); 
	int input[] = new int[size];
	int bit[] = new int[size+1];
	int bit2[] = new int[size+1];
	for (int i = 0; i < input.length; i ++) {
		input[i] = random.nextInt(16) - 8;
	}
        FenwickTree ft = new FenwickTree();
//	System.out.println(Arrays.toString(input));
	System.out.println("First Constructor");
	System.out.println(String.format("Count: %d", ft.count));
//	System.out.println(Arrays.toString(bit));
	System.out.println(String.format("Scale: %f", ((double) ft.count) / ((double) size))); 
    	/*********************************/
	ft.createTree2(input, bit2);
	for (int i = 1; i <= size; i ++) {
	    if (bit[i] != bit2[i])
		System.err.println("Wrong!" + i);
	}
	/*********************************/
//	System.out.println(Arrays.toString(bit2));
	System.out.println("Second Constructor");
	System.out.println(String.format("Count: %d", ft.count));
	System.out.println(String.format("Scale: %f", ((double) ft.count) / ((double) size)));
    }
}
