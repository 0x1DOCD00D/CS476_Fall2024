public class ExceptionMaker {
    int f(int p) throws Exception {
        if (p < 0) {throw new Exception("hi from Java");}
        else {return p;}
    }

    public static void main(String[] args) {
        try {
            new ExceptionMaker().f(-1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
