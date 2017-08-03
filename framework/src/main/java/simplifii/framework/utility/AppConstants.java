package simplifii.framework.utility;

public interface AppConstants {

    public static final String DEF_REGULAR_FONT = "Ubuntu-R.ttf";
    public static final String EXPLORITAGE_IMAGES_DOWNLOAD_FOLDER_EXTERNAL="ExploritageImages";
    public static final String EXPLORITAGE_AUDIO_DOWNLOAD_FOLDER_EXTERNAL="ExploritageAudio";
    String APP_LINK = "https://drive.google.com/file/d/0B8wKJnD6sONHeXlUbm5pOTk4dGM/view?usp=sharing";

    interface REQUEST_CODES {

        int CREATE_ROLE = 1;

    }


    public static interface VALIDATIONS {
        String EMPTY = "empty";
        String EMAIL = "email";
        String MOBILE = "mobile";
    }

    interface ERROR_CODES {

        public static final int UNKNOWN_ERROR = 0;
        public static final int NO_INTERNET_ERROR = 1;
        public static final int NETWORK_SLOW_ERROR = 2;
        public static final int URL_INVALID = 3;
        public static final int DEVELOPMENT_ERROR = 4;

    }

    public static interface PAGE_URL {

    /*    String ABOUT_US = "http://www.google.com";
        String TnC = "http://www.google.com";
        String PRIVACY_POLICY = "http://www.google.com";*/

        String BASE_URL = "https://api.backendless.com/v1/data/";
        String CITY_GUIDE = BASE_URL + "CityGuides";
        String CITY_DETAILS = BASE_URL + "CityDetail";
        String PLACE_TO_VISIT = BASE_URL + "PlacesToVisit";
        String POST_FEEDBACK = BASE_URL + "Feedback";
        String ABOUT_TEXT_URL = "https://api.backendless.com/7B1F2DAD-9A9F-00DD-FF80-B391C0149500/v1/files/media/VoiceOfHeritage_About_text.txt";
        String HOWTOUSE_TEXT_URL = "https://api.backendless.com/7B1F2DAD-9A9F-00DD-FF80-B391C0149500/v1/files/media/VoiceOfHeritage_HowToUse_Text.txt";
        String SHARE_TEXT_URL = "https://api.backendless.com/v1/data/cctyGuides";

       /* String EMPLOYEES = "/employees";
        String ORGANISATION = "/org";
        String GET = "/get";
        String ACTION = "/action";
        String EDIT = "/edit";*/
    }

    public static interface PREF_KEYS {

        String KEY_LOGIN = "IsUserLoggedIn";
        String KEY_USERNAME = "username";
        String KEY_PASSWORD = "password";
        String ACCESS_CODE = "access";
        String APP_LINK = "appLink";


        String USER_ID = "user_id";
        String IS_LOGIN = "is_login";
        String USER_INSTANCE = "user_intance";
        String SELLER_NAME = "user_name";
        String SELLER_MOBILE = "Seller_mobile";
        String SELLER_EMAIL = "seller_email";
        String SELLER_INSTANCE = "seller Instance";
        String USER_TOKEN = "user_token";
        String USER_EMAIL = "user_email";
        String USER_NAME = "username";
        String OBJECT_ID = "object_id";
        String PAST_CURRENT_ILLNESS = "past_current_illness";
        String DIAGNOSIS = "diagnosis";
        String ALLERGY = "allergy";
        String SYSTEM_REVIEW = "system_review";
        String FURTHER_ANALIST = "ferther";
        String VITALS = "vitals";
        String MEDICINES = "medicine";
        String DICTIONARY_ADDED = "dictionary_added";
        String ADDING_SUGETIONS = "adding_suggetions";
        String DIAGNOSIS_DATA = "diagnosis_data";
        String CREATE_ROLE_INSTANCE = "role_instance";
        String USER_SESSION = "userSession";
        String FCM_TOKEN = "fcm_token";
        String KEY_EMP_LIST_JSON = "empListJson";
        String KEY_ACCOUNT_LIST_JSON = "accountList";
        String SAVED_AUDIO_PATH = "audiopathsaved";
        String IS_DOWNLOADED = "isdowload";
        String KEY_CITY_GUIDE_JSON = "cityguideJson";
        String KEY_PLACE_TO_VISIT_JSON = "placeToVisitJson";
        String TEXT_FILE_URL = "textFileUrl";
        String KEY_AUDIO_FILE = "audioFile";
    }

    public static interface BUNDLE_KEYS {
        public static final String KEY_SERIALIZABLE_OBJECT = "KEY_SERIALIZABLE_OBJECT";
        public static final String FRAGMENT_TYPE = "FRAGMENT_TYPE";
        String EXTRA_BUNDLE = "bundle";
        String NUMBER = "number";
        String SEARCH = "search";
        String CREATE_ROLE_DATA = "createRoleData";
        String SELECTED_DEPARTMENT = "selectedDepartment";
        String SELECTED_CENTER = "selectedCenter";
        String SELECTED_COST_CENTER = "selectedCostCenter";
        String USER_ID = "userId";
        String USER_NAME = "userName";
        String USER_PROFILE_RESPONSE = "userProfile";
        String DATE_API_FORMAT = "dateApiFormat";
        String DATE_UI_FORMAT = "dateUiFormat";
        String PHONE = "phone";
        String APPLICATION_ID = "applicationId";
        String IS_UPVOTE_STAR = "isUpvoteStar";
        String KEY_URL = "keyUrl";
        String KEY_TITLE = "keyTitle";
        String OTP = "otp";
        String CARD_ID = "cardId";
        java.lang.String NOTIF_TYPE = "notifType";
        String IS_FROM_PUSH = "isFromPush";
        String TOOLBAR_TITLE = "toolbarTitle";
        String LEAVE_TYPE = "leaveType";
        String EDIT_TRANSACTION = "editTransaction";
        String DUE_DATE = "dueDate";
        String AMOUNT = "amount";
        String COMMENT = "comment";
        String STATUS = "status";
        String ACCOUNT_ID = "accountId";
        String ACCOUNT_NAME = "accountName";
        String CITY_LIST = "citylist";
        String CITY_DATA = "cityData";
        String PLACE_DATA = "placeData";
        String PLACE_DATA_LIST = "placeDataList";
        String CITY_OBJECT = "cityObject";
        String SUBSITE_IMG_URL = "subsiteImageUrl";
        String CITY_OBJECT_SITE_MAP = "cityObjectSiteMap";
        String CITY_DETAIL_OBJ = "cityDetailObj";
        String KEY_IMAGES = "keyImages";
        String AUDIO_IMAGE_URL_KEY = "audioImageUrlKSey";
        String MAP_IMAGE_URL_KEY = "mapImageUrlKey";
        String AUDIO_FILE_URL_KEY = "audioFileUrlKey";
        String PLACE_NAME_KEY = "placeNameKey";
        String CITY_DETAIL = "cityDetail";
        String PLACE_DESCRIPTION_URL_KEY = "placeDescription";
    }

    public static interface FRAGMENT_TYPE {
        int EDIT_PROFILE = 1;
        int CHANGE_MOBILE_NUMBER = 2;
        int CHANGE_REPORTING_MANAGER = 3;
    }


    public interface TASK_CODES {


        int AVAILABLE_CITY_GUIDES = 1;
        int UPLOAD_IMAGE = 2;
        int CITY_DETAILS = 3;
        int PLACES_TO_VISIT = 4;
        int PLACE_DETAILS = 5;
        int POST_FEEDBACK = 6;
        int SHARE_TEXT=7;
    }

    public static interface MEDIA_TYPES {
        String IMAGE = "img";
        String AUDIO = "audio";
        String VIDEO = "video";
    }

    public interface PARAMS {

        String LAT = "latitude";
        String LNG = "longitude";
    }


    public static interface VIEW_TYPE {
        int CARD_MY_TEAM = 0;
        int TEAM_FRAGMENT_SELF = 1;
    }

}
