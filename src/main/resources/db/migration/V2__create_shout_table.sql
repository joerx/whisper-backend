CREATE SEQUENCE shout_id_seq INCREMENT 1 MINVALUE 1 START 8518 CACHE 1;

CREATE TABLE "public"."shout" (
                                  "id" integer DEFAULT nextval('shout_id_seq') NOT NULL,
                                  "message" character varying(255) NOT NULL,
                                  "username" character varying(50) NOT NULL,
                                  "timestamp" timestamp NOT NULL,
                                  CONSTRAINT "shout_id" PRIMARY KEY ("id")
) WITH (oids = false);

CREATE INDEX "shout_username" ON "public"."shout" USING btree ("username");
