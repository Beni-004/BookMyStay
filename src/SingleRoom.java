class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 200, 100);
    }
    @Override
    public String getRoomType() {
        return "Single Room";
    }
}