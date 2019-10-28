DROP TABLE IF EXISTS "shout";
DROP SEQUENCE IF EXISTS shout_id_seq;
CREATE SEQUENCE shout_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE TABLE "public"."shout" (
                                  "id" integer DEFAULT nextval('shout_id_seq') NOT NULL,
                                  "message" character varying(255) NOT NULL,
                                  "username" character varying(50) NOT NULL,
                                  "timestamp" timestamp NOT NULL,
                                  CONSTRAINT "shout_id" PRIMARY KEY ("id")
) WITH (oids = false);

CREATE INDEX "shout_username" ON "public"."shout" USING btree ("username");

INSERT INTO "shout" ("id", "message", "username", "timestamp") VALUES
(1,	'Hello world',	'joerx',	'2019-10-26 02:18:19.754456'),
(2,	'Another shout',	'james',	'2019-10-28 03:39:42.187641'),
(3,	'I can''t hear you!',	'melinda',	'2019-10-28 03:40:07.206258');