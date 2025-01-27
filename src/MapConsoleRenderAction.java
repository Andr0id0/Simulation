public class MapConsoleRenderAction extends Action<Void> {

    @Override
    public Void doAction(Map map) {
        renderMap(map);
        return null;
    }




    ///  если кто подскажет как быть в такой ситуации буду благодарен
    ///  нужно переопределять метод с без нужного (как по мне это не красиво???)
    ///  мб есть выход из такой ситуации?
    @Override
    public Void doAction() {
        return null;
    }

    private void renderMap(Map map) {
        MapConsoleRenderer consoleRenderer = new MapConsoleRenderer(map);
        consoleRenderer.render();
    }

}
