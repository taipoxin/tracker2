
/*
use database work
 */

/*
key
work_date
iterations
time
*/

CREATE TABLE IF NOT EXISTS work_days(
	id INT NOT NULL AUTO_INCREMENT,
	work_date DATE NOT NULL,
	iterations TINYINT NOT NULL,
	work_time TEXT NOT NULL,
	PRIMARY KEY(id)
);



/*
key
ddate
ttime
duration
*/
CREATE TABLE IF NOT EXISTS work_iters(
	id       INT  NOT NULL AUTO_INCREMENT,
	ddate    DATE NOT NULL,
	ttime    TIME NOT NULL,
	duration TIME NOT NULL,
	PRIMARY KEY(id)
);
