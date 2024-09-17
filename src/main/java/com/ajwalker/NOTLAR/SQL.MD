sql state veri tipini güncelleyen kodlar
```
ALTER TABLE tblusercomment
ALTER COLUMN state DROP DEFAULT;

ALTER TABLE tbluser
ALTER COLUMN state DROP DEFAULT;

ALTER TABLE tbllike
ALTER COLUMN state DROP DEFAULT;

ALTER TABLE tblvideo
ALTER COLUMN state DROP DEFAULT;


ALTER TABLE tblusercomment
ALTER COLUMN state TYPE INTEGER USING state::integer;

ALTER TABLE tbllike
ALTER COLUMN state TYPE INTEGER USING state::integer;


ALTER TABLE tbluser
ALTER COLUMN state TYPE INTEGER USING state::integer;


ALTER TABLE tblvideo
ALTER COLUMN state TYPE INTEGER USING state::integer;

ALTER TABLE tblusercomment
ALTER COLUMN state SET DEFAULT 1;

ALTER TABLE tbluser
ALTER COLUMN state SET DEFAULT 1;

ALTER TABLE tbllike
ALTER COLUMN state SET DEFAULT 0;

ALTER TABLE tblvideo
ALTER COLUMN state SET DEFAULT 1;
```