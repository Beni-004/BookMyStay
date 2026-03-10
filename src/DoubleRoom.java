class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 200);
    }
    @Override
    public String getRoomType(){
        return "Double room";
    }
}
