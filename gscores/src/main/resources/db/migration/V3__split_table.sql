DROP TABLE IF EXISTS exam_result;

CREATE TABLE "students" (
                            "sbd" varchar(10) PRIMARY KEY ,
                            "foreign_language_code" varchar(10),
                            "created_at" timestamptz DEFAULT (now()),
                            "updated_at" timestamptz DEFAULT (now())
);


CREATE TABLE "subjects" (
                            "id" BIGSERIAL PRIMARY KEY,
                            "name" varchar(50) UNIQUE NOT NULL,
                            "created_at" timestamptz DEFAULT (now()),
                            "updated_at" timestamptz DEFAULT (now())
);


CREATE TABLE "scores" (
                          "id" BIGSERIAL PRIMARY KEY,
                          "sbd" varchar(10) NOT NULL,
                          "subject_id" bigint NOT NULL,
                          "score" numeric(5,3),
                          "created_at" timestamptz DEFAULT (now()),
                          "updated_at" timestamptz DEFAULT (now()),
                          CONSTRAINT unique_std_id_subject_id UNIQUE (sbd, subject_id)
);


CREATE TABLE "exam_groups" (
                               "id" BIGSERIAL PRIMARY KEY,
                               "group_name" varchar(10) UNIQUE NOT NULL,
                               "created_at" timestamptz DEFAULT (now()),
                               "updated_at" timestamptz DEFAULT (now())
);


CREATE TABLE "group_subjects" (
                                  "group_id" bigint REFERENCES "exam_groups"("id"),
                                  "subject_id" bigint REFERENCES "subjects"("id"),
                                  PRIMARY KEY ("group_id", "subject_id")
);

ALTER TABLE "scores"
    ADD FOREIGN KEY ("sbd") REFERENCES "students" ("sbd") ON DELETE CASCADE;

ALTER TABLE "scores"
    ADD FOREIGN KEY ("subject_id") REFERENCES "subjects" ("id") ON DELETE CASCADE;