# Proje Açıklaması

Projenin amacı, Kullanıcıların kendi kullanıcı girişlerini sağlayarak sistemde bulunan videoları izleyebilme,
beğenip yorumları görntüleyebilme özellikleri ile video platformu benzeri bir uygulama tasarımıdır.
Bu projedeki yazılımcı ekibimizin amacı ise katmanlı mimariyi benimseyerek katmanlı mimariyi içselleştirmek.

# GUI Özelliklerine Ufak Bir Bakış 
Proje ilk çalıştığı anda kullanıcıyı ana menu karşılayacaktır.

burada çıkan seçenekler içerisinde kullanıcı herhangi bir giriş yapmadan uygulama içerisinde gezinebilir,
videoları izleyebilir(Bunun içerisinde Trend videoları görüntüle gibi özellikler bulunmaktadır) ve yorumları okuyabilir. 
Fakat beğeni sistemini kullanabilmek ve yorum yapabilmek için kullanıcı giriş sistemine yönlendirilecektir.

Giriş yapan kullanıcılar ise anonim kullanıcılardan bağımsız olarak kendi videolarını görüntüleyebilir.
Videoları beğenip yorum yapabilirler. 


# Project Description
The aim of the project is to design a video platform-like application that allows users to watch videos, 
like and view comments, and other features, by providing their own user login credentials.

The goal of our development team is to adopt a layered architecture and internalize it.

# A Brief Look at GUI Features
When the project first runs, the user will be greeted by the main menu.

Here, the user can browse the application without logging in, watch videos (including features like trending videos), 
and read comments. However, to use the like system and make comments, the user will be directed to the login system.

Users who log in can view their own videos independently of anonymous users, like and comment on videos.

# For Developers

## SQL DB Java Settings (JDBC)

Your project uses the postgreSQL database. 
The JDBC driver is com.mysql.cj.jdbc and 
the connection URL is jdbc:postgresql://localhost:3306/mydatabase (example URL) 
The username is username and the password is password.



## CREATE TABLES

### tbluser

```sql
CREATE TABLE IF NOT EXISTS public.tbluser
(
    id bigint NOT NULL DEFAULT nextval('tbluser_id_seq'::regclass),
    username character varying COLLATE pg_catalog."default",
    email character varying COLLATE pg_catalog."default",
    password character varying COLLATE pg_catalog."default",
    state integer DEFAULT 1,
    CONSTRAINT tbluser_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tbluser
    OWNER to postgres;
);
```

### tblvideo
```sql
CREATE TABLE IF NOT EXISTS public.tblvideo
(
    id bigint NOT NULL DEFAULT nextval('tblvideo_id_seq'::regclass),
    creator_id bigint,
    title character varying(255) COLLATE pg_catalog."default",
    content character varying(255) COLLATE pg_catalog."default",
    description character varying(255) COLLATE pg_catalog."default",
    state integer DEFAULT 1,
    CONSTRAINT tblvideo_pkey PRIMARY KEY (id),
    CONSTRAINT tblvideo_creator_id_fkey FOREIGN KEY (creator_id)
        REFERENCES public.tbluser (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tblvideo
    OWNER to postgres;
```


### tbllike
```sql
CREATE TABLE IF NOT EXISTS public.tbllike
(
    id bigint NOT NULL DEFAULT nextval('tbllike_id_seq'::regclass),
    user_id bigint,
    video_id bigint,
    state integer DEFAULT 0,
    CONSTRAINT tbllike_pkey PRIMARY KEY (id),
    CONSTRAINT tbllike_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.tbluser (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tbllike
    OWNER to postgres;
```
### tblusercomment
```sql
CREATE TABLE IF NOT EXISTS public.tblusercomment
(
    id bigint NOT NULL DEFAULT nextval('tblcomment_id_seq'::regclass),
    video_id bigint,
    user_id bigint,
    comment character varying COLLATE pg_catalog."default",
    state integer DEFAULT 1,
    CONSTRAINT tblcomment_pkey PRIMARY KEY (id),
    CONSTRAINT tblcomment_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.tbluser (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tblusercomment
    OWNER to postgres;
```
