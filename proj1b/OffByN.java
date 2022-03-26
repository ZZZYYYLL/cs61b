public class OffByN implements CharacterComparator{

    private int count;
    public OffByN(int n) {
        count = n;
    }


    @Override
    public boolean equalChars(char x, char y) {

        if (Math.abs(x - y) == count) {
            return true;
        } else {
            return false;
        }
    }


}
