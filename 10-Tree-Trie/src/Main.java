import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String fileName="D:\\git_repo\\Data-Structures-Learning\\10-Trie\\src\\a-tale-of-two-cities.txt";
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(fileName,words)) {
            System.out.println("------BSTSet------");
            testTreeSet(new BSTSet<>(),words);
            System.out.println("------TrieSet------");
            testTreeSet(new TrieSet(),words);
        }
    }

    private static void testTreeSet(Set<String> set,ArrayList<String> words) {
        StringBuilder sb = new StringBuilder();

        long startTime = System.nanoTime();
        for(String word:words) {
            set.add(word);
        }
        sb.append("total size is "+words.size()+",and different is "+set.getSize()+", add words cost " + (System.nanoTime()-startTime)/1000000000.0+" s, ");

        long midTime = System.nanoTime();
        for(String word:words) {
            set.contains(word);
        }
        sb.append(" contains words cost "+(System.nanoTime()-midTime)/1000000000.0+"s, ");

        long endTime = System.nanoTime();
        for(String word:words) {
            set.remove(word);
        }
        sb.append(" remove words cost "+(System.nanoTime()-endTime)/1000000000.0+"s.");

        System.out.println(sb.toString());
    }
}
