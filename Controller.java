class Controller {
    Model model ;
    View view ;
    
    public Controller(){
        model = new Model(this) ;
        view = new View(this);
    }
}
