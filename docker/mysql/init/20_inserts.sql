INSERT INTO calendar.schedules (name, date, all_day)
VALUES ("Buy somethigng", "20191020", true);
INSERT INTO calendar.schedules (name, date, all_day)
VALUES ("Meet Someone", "20191021", true);
INSERT INTO calendar.schedules (name, date, all_day)
VALUES ("Workout", "20191021", true);

INSERT INTO calendar.additional_information (schedule_id, name, value)
VALUES (1, "url", "https://example.com/hogehoge");
INSERT INTO calendar.additional_information (schedule_id, name, value)
VALUES (1, "location", "Tokyo");
INSERT INTO calendar.additional_information (schedule_id, name, value)
VALUES (2, "url", "https://example.com/foofoo");
INSERT INTO calendar.additional_information (schedule_id, name, value)
VALUES (2, "location", "Osaka");
INSERT INTO calendar.additional_information (schedule_id, name, value)
VALUES (3, "location", "Golds gym");

INSERT INTO calendar.categories (name)
VALUES ("communication");
INSERT INTO calendar.categories (name)
VALUES ("routine");
INSERT INTO calendar.categories (name)
VALUES ("self-investment");

INSERT INTO calendar.schedules_categories (schedule_id, category_id)
VALUES (1, 2);
INSERT INTO calendar.schedules_categories (schedule_id, category_id)
VALUES (2, 1);
INSERT INTO calendar.schedules_categories (schedule_id, category_id)
VALUES (3, 2);
INSERT INTO calendar.schedules_categories (schedule_id, category_id)
VALUES (3, 3);

