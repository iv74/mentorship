CREATE TABLE forum_topics (
  id INTEGER PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY(START WITH 1),
  subject VARCHAR(35),
  text VARCHAR(255),
  is_closed INT,
  date_created DATE
);