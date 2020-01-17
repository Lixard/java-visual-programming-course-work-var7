CREATE TABLE "questionnaire" (
	"id" serial NOT NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT "questionnaire_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "answers" (
	"id" serial NOT NULL,
	"questionnaire_id" integer NOT NULL,
	"question_number" integer NOT NULL,
	"answer" varchar(255) NOT NULL,
	CONSTRAINT "answers_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);




ALTER TABLE "answers" ADD CONSTRAINT "answers_fk0" FOREIGN KEY ("questionnaire_id") REFERENCES "questionnaire"("id");

