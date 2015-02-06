package co.mobilemakers.contactstudio;

/**
 * Contact data model
 *
 * Created by ariel.cattaneo on 05/02/2015.
 */
public class ContactModel {

    protected String mName;
    protected String mNickname;
    protected String mPhotoURL;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getNickname() {
        return mNickname;
    }

    public void setNickname(String nickname) {
        this.mNickname = nickname;
    }

    public String getPhotoURL() {
        return mPhotoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.mPhotoURL = photoURL;
    }
}
