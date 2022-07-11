public class Rq {

    String url;
    String path;
    String query;

    public Rq(String url) {
        this.url = url;
        String[] urlBits = url.split("\\?");
        this.path = urlBits[0];

        if (urlBits.length == 2) {
            this.query = urlBits[1];
        }
    }

    public int getIntParam(String paramName, int defaultValue) {
        if (query == null) {
            return defaultValue;
        }

        String[] urlBits = query.split("&");

        for (String urlBit : urlBits) {
            String[] paramNameAndValue = urlBit.split("=", 2);
            String paramType = paramNameAndValue[0];
            String paramValue = paramNameAndValue[1];

            if (paramName.equals(paramType)) {
                return Integer.parseInt(paramValue);
            }
        }

        return defaultValue;
    }

    public String getPath() {
        return this.path;
    }
}
