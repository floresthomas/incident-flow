CREATE TABLE incidents (
                           id INT PRIMARY KEY CHECK (id > 0),
                           category VARCHAR(50) NOT NULL CHECK (TRIM(category) <> ''),
                           state VARCHAR(30) NOT NULL CHECK (state IN ('OPEN', 'CLOSED')),
                           duration INT NOT NULL CHECK (duration >= 0)
);