class SVCreator{
    SVCreator() throws Exception{
        new SVServer();
        new SVClient("localhost");
        new SVClient("localhost");
    }

    public static void main(String[] args) {
        try {
            new SVCreator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}