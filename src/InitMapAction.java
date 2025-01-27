public class InitMapAction extends  Action<Map> {


    @Override
    public Map doAction() {
        return initMap();
    }

    ///  если кто подскажет как быть в такой ситуации буду благодарен
    ///  нужно переопределять метод который не лишние  передаваемые параметров (как по мне это не красиво???)
    ///  мб есть выход из такой ситуации?
    @Override
    public Map doAction(Map map) {
        return initMap();
    }


    private Map initMap() {
        return new Map();
    }


}
