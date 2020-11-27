package csvParser;



public class Input {
    private static Property ff;

    public static Property newIn() {
        return getIn(new Asker(System.in, System.out));
    }

    public static Property getIn(Asker asker)
    {
        String plus = asker.ask("Введите итоговый сепаратор(enter чтобы пропустить):");
        if (!plus.equals("")) {
            String to = asker.ask("Введите путь к директории:");
            if (!to.equals("")){
                    String sep = asker.ask("Введите сепаратор:");
                    if (!sep.equals(""))
                        ff=new Property(plus,to,sep.charAt(0));
                    else
                        ff=new Property(plus,to);
            }
            else
                ff=new Property(plus);
        }
        else{
            ff= new Property();
        }
        return ff;
    }
}
