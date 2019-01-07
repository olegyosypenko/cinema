
CREATE USER 'cinema_admin'@'localhost' IDENTIFIED BY 'cinema_admin';
CREATE DATABASE  IF NOT EXISTS `db_cinema` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_cinema`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: db_cinema
-- --------------------------------------------/----------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `films`
--

DROP TABLE IF EXISTS `films`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `films` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `name_en` varchar(100) DEFAULT NULL,
  `genre` varchar(15) DEFAULT NULL,
  `genre_en` varchar(15) DEFAULT NULL,
  `director` varchar(45) DEFAULT NULL,
  `director_en` varchar(45) DEFAULT NULL,
  `rate` float unsigned zerofill DEFAULT NULL,
  `description` text,
  `description_en` text,
  `image_link` text,
  `image_link_en` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_en_UNIQUE` (`name_en`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `films`
--

LOCK TABLES `films` WRITE;
/*!40000 ALTER TABLE `films` DISABLE KEYS */;
INSERT INTO `films` VALUES (26,'Наркокур′єр','The  Mule','Драма','Drama','Клінт Іствуд','Clint Eastwood',0000000006.3,'Ерлу Стоуну вже за 80, він самотній ветеран Другої світової війни і невдаха підприємець, чий бізнес тріщить по швах. У скрутному становищі Стоун погоджується на роботу перевізника. Його вантаж - величезні партії героїну, а його роботодавці - впливові мексиканські гангстери. За старим починає полювання суворий федерал ...','Earl Stone is a 90-year-old horticulturist and Korean War veteran who is facing financial ruin and is estranged from his family. Desperate for money, he becomes a \"mule\" transporting cocaine through Illinois for a Mexican drug cartel. Facing little suspicion due to his age, race, spotless criminal history and strict adherence to driving laws, Earl is soon trusted with huge amounts of drugs and paid equally large amounts of cash...','https://kinoafisha.ua/upload/2018/10/films/8596/1540562943narkokurer.jpg','https://st.kp.yandex.net/images/film_iphone/iphone360_1142526.jpg'),(27,'Красивий бізнес','I Feel Good','Комедія','Comedy','Гюстав Керверн','Gustave de Kervern',0000000006.4,'Авантюрний та непосидючий Жак (Жан Дюжарден) залишився безхатьком, після того як його вигнали з дому батьки. Після деяких поневірянь та життя у гаражі, прихисток йому дала рідна сестра, але це не може тривати вічно. Аби покращити своє становище, Жак вирішив збагатитися. Найлегший та найцікавіший бізнес, на його думку, це пластична хірургія.','Jacques, an ambitious man pushed from the home by his old parents, decides one fine day to become rich and famous by exploiting the vein of cosmetic surgery low cost. To develop his business plan, he took refuge at his sister Monique, director of an Emmaus village. By dint of giving them a better future, he will eventually take a whole group of companions to a clinic in Romania, to all come back more beautiful.','https://kino-teatr.ua/public/main/films/poster_5b7261f379347.jpg','https://s3.eu-central-1.amazonaws.com/listmusor/production/afisha/75079/5c12260f02b9c.jpg'),(28,'Бамблбі','Bumblebee','Екшн','Actiohn','Тревіс Найт','Travis Knight',0000000007.3,'Події розгортаються у 1987 році. У невеличкому містечку на каліфорнійському узбережжі живе дівчина Чарлі (Гейлі Стайнфілд), якій ось-ось має виповнитись 18 років. Якось під час прогулянки вона знаходить покинуту машину, фольксваген «Жук» жовтого кольору. Скоро дівчина побачить, що її знахідка – це не купа старого брухту, а жива істота. Це Бамблбі. Після висадки на Землю він переховується.','On the run in the year 1987, Bumblebee the Autobot seeks refuge in a junkyard in a small California beach town. Charlie, on the brink of turning 18 years old and trying to find her place in the world, soon discovers the battle-scarred and broken Bumblebee. When Charlie revives him, she quickly learns that this is no ordinary yellow Volkswagen.','https://www.brd24.com/up/iblock/5de/5deb0af93bf8b49483375a9100fb50fb.jpg','https://www.film.ru/sites/default/files/images/28259791-1072480.jpg'),(29,'Дублікат','Jonathan','Драма','Drama','Білл Олівер','Bill Oliver',0000000005.8,'У головного героя роздвоєння особи: у ньому вживаються 2 людини з розділеною активністю на день і ніч. Встановлені негласні правила дозволяють двом особам мирно співіснувати, але іноді правила порушуються...','Jonathan leaves the office everyday at noon. When he gets home, he goes to sleep. Every morning he wakes up and there is a breakfast prepared for him along with a video telling him about the second part of his day.','http://kinosvit4u.at.ua/_pu/28/08030318.jpg','https://resizing.flixster.com/jfHuITOyBVtG4NhF3DNJGLQAoUU=/fit-in/200x296.2962962962963/v1.bTsxMjg4MTEwMztqOzE3OTY5OzEyMDA7MTM4MjsyMDQ4'),(30,'Людина-павук: Навколо всесвіту','Spider-Man: Into the Spider-Verse','Пригоди','Adventure','Боб Персічетті','Robert Persichetti',0000000006.2,'Юний Майлз Моралес з Нью-Йорка живе в світі неймовірного потенціалу всесвітів Людини-павука, де екіпіровку супергероя носить не тільки він.','Bitten by a radioactive spider in the subway, Brooklyn teenager Miles Morales suddenly develops mysterious powers that transform him into the one and only Spider-Man. When he meets Peter Parker, he soon realizes that there are many others who share his special, high-flying talents. Miles must now use his newfound skills to battle the evil Kingpin, a hulking madman who can open portals to other universes and pull different versions of Spider-Man into our world','https://upload.wikimedia.org/wikipedia/uk/0/0b/%D0%9F%D0%BE%D1%81%D1%82%D0%B5%D1%80_%D0%B4%D0%BE_%D1%84%D1%96%D0%BB%D1%8C%D0%BC%D1%83_%C2%AB%D0%9B%D1%8E%D0%B4%D0%B8%D0%BD%D0%B0-%D0%BF%D0%B0%D0%B2%D1%83%D0%BA-_%D0%9D%D0%B0%D0%B2%D0%BA%D0%BE%D0%BB%D0%BE_%D0%B2%D1%81%D0%B5%D1%81%D0%B2%D1%96%D1%82%D1%83%C2%BB.png','https://www.bleedingcool.com/wp-content/uploads/2018/11/spiderman_into_the_spiderverse_ver3_xlg.jpg?x70969'),(31,'Фантастичні звірі: Злочини Ґріндельвальда','Fantastic Beasts: The Crimes of Grindelwald','Фентезі','Fantasy ','Девід Йетс','David Yates',0000000006.8,'Пригоди популярного магічного заоолога і письменника Ньюта Скамандера, що відбуваються за 70 років до того, як Гаррі Поттер прочитає його книгу...','In an effort to thwart Grindelwald\'s plans of raising pure-blood wizards to rule over all non-magical beings, Albus Dumbledore enlists his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.','https://kinoafisha.ua/upload/2016/11/films/7457/1539675616fantasticseskie-zveri-i-gde-ih-iskat-2.jpg','https://static1.squarespace.com/static/51b3dc8ee4b051b96ceb10de/5bbe61a8e2c48399c89a51a3/5bbe61a8b208fc8a5e0f728b/1539203514808/FNBST2_ACTION_GRINDLEWALD_VERT_MAIN_DOM_2764x4096_master.jpg'),(32,'Патрік ','Patrick','Сімейний','Family','Менді Флетчер','Mandie Fletcher',0000000005.7,'Головна героїня Сара живе в справжньому хаосі. В її сумбурне життя вривається розпещений мопс по кличці Патрік, якого заповідала їй бабуся.','A woman\'s chaotic life becomes more complicated when she inherits her grandmother\'s dog.','https://kinoafisha.ua/upload/2018/06/films/8453/1532615098patrik.jpg','http://www.gstatic.com/tv/thumb/movieposters/15669273/p15669273_p_v8_ab.jpg'),(33,'Аквамен','Aquaman','Бойовик','Action','Джеймс Ван','James Wan',0000000007.6,'Артур Каррі незвичайний хлопчина - він сильний, дуже спритний і феноменально швидкий. Головна його особливість - він дихає під водою і базікає з рибами. Його мати - Королева Атлантиди у вигнанні, а батько - хранитель маяка. Чи зможе Аквамен запобігти війні між двома світами...','Once home to the most advanced civilization on Earth, the city of Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people -- and then the surface world. Standing in his way is Aquaman, Orm\'s half-human, half-Atlantean brother and true heir to the throne. With help from royal counselor Vulko, Aquaman must retrieve the legendary Trident of Atlan and embrace his destiny as protector of the deep.','https://kinoafisha.ua/upload/2015/04/films/6736/1531814536akvamen.jpg','http://hitshow.hl.ua/i/1545262616-Akvamen.jpg'),(34,'Снігові перегони','Racetime','Пригоди','Adventure','Бенуа Годбут','Benoît Godbout',0000000006.8,'Засніжені гірські схили в канадському живописному селі слугують для дітвори майданчиком для спортивних змагань. Вони самі майструють собі санки та влаштовують на них перегони. А заради перемоги суперники йдуть на підступні хитрощі...','Frankie-Four-Eyes and his team face off against conceited newcomer Zac, who is not above cheating a little to win a sled race.','https://kinoafisha.ua/upload/2018/08/films/8543/1yzsehtasnejne-gonki.jpg','http://www.northernstars.ca/wp-content/uploads/2018/11/Racetime-2018-posterLRG.jpg'),(35,'Мері Поппінс повертається','Mary Poppins Returns','Фентезі','Fantasy','Роб Маршал','Rob Marshall',0000000007.3,'Неповторна Мері Поппінс повертається в родину Бенксів, щоб допомогти їм впоратися з життєвими негараздами.','Now an adult with three children, bank teller Michael Banks learns that his house will be repossessed in five days unless he can pay back a loan. His only hope is to find a missing certificate that shows proof of valuable shares that his father left him years earlier. Just as all seems lost, Michael and his sister receive the surprise of a lifetime when Mary Poppins -- the beloved nanny from their childhood -- arrives to save the day and take the Banks family on a magical, fun-filled adventure.','https://image.tmdb.org/t/p/original/48oXEECaERUH7ylJmQSnk1Ifbyg.jpg','https://akns-images.eonline.com/eol_images/Entire_Site/2018109/rs_1024x1376-181109113253-1024.mary-poppins-ew-cover.11918.jpg?fit=inside|900:auto&output-quality=90'),(36,'Смертні машини','Mortal Engines','Екшн','Action','Крістіан Ріверс','Christian Rivers',000000000008,'Глобальний військовий конфлікт постапокаліптичного майбутнього перетворив міста на машини, що рухаються та вступають в сутички між собою за залишки природних ресурсів та технологій.','Hundreds of years after civilization was destroyed by a cataclysmic event, a mysterious young woman, Hester Shaw (Hera Hilmar), emerges as the only one who can stop London — now a giant, predator city on wheels — from devouring everything in its path.','http://cinema.vn.ua/wp-content/uploads/2018/11/Mortal-Engines_One-70x101.jpg','https://play.soundplate.com/uploads/img/5b99425c0c914.jpg'),(37,'Лускунчик і чотири королівства','The Nutcracker and the Four Realms','Фентезі','Fantasy','Лассе Халльстрем','Lasse Hallström',0000000005.6,'Популярна історія розкриється з темної сторони. З початку сутінків Лускунчик проведе глядачів в країну чудес, де йде безкомпромісна війна добра і зла, любові і гніву.','Young Clara needs a magical, one-of-a-kind key to unlock a box that contains a priceless gift. A golden thread leads her to the coveted key, but it soon disappears into a strange and mysterious parallel world. In that world, she meets a soldier named Phillip, a group of mice and the regents who preside over three realms. Clara and Phillip must now enter a fourth realm to retrieve the key and restore harmony to the unstable land.','https://kinoafisha.ua/upload/2017/07/films/7910/1yw8iceasxelkuncsik-i-csetre-korolevstva.jpg','https://www.udiscovermusic.com/wp-content/uploads/2018/10/Nutcracker-and-the-four-Realms-Cover-Art-web-optimised-820.jpg'),(38,'Чому ми креативні? ','Why Are We Creative: The Centipede\'s Dilemma','Документальний','Documentary','Герман Васке','Hermann Vaske',0000000007.2,'Ще жодному вченому не вдалося розкласти феномен креативності по поличках і пояснити, звідки народжується наша творчість, як її контролювати, розвивати та стимулювати. Автор фільму-відкриття, Герман Васке, теж переймався цим питанням і зважився на власне дослідження. Впродовж понад тридцяти років режисер збирав інтерв’ю з художниками й мислителями, серед яких лауреати Нобелівської премії й \"Оскара\", зірки образотворчого мистецтва, музики, кіновиробництва, літератури, філософії, політики, бізнесу й науки. Усім їм він ставив одне запитання: \"Чому ви креативні?\". Встигніть побачити як ніяковіють, сміються але, врешті-решт, відповідають на це запитання Квентін Тарантіно, Девід Лінч, Демієн Херст, Марина Абрамович, Йоко Оно, Такеші Кітано, Девід Бові та інші.','As part of a personal quest, for over 30 years director Hermann Vaske filmed the world\'s most intriguing artists and thinkers, including over 50 luminaries, among them Academy Award and Nobel Prize winners, from the fields of visual art, music, acting, philosophy, politics, business and science, posing the question: \"Why are you creative?\" Vaske\'s subjects include: David Bowie, Ai Weiwei, Björk, Wim Wenders, Philippe Stark, Yoko Ono, John Hegarty, David Lynch, Yohji Yamamoto, Damien Hirst, Angelina Jolie, Nobuyoshi Araki, Quentin Tarantino, Bono, Nick Cave, Neo Rauch, Stephen Hawkins, the Dalai Lama, Peter Ustinov, Marina Abramovic, Diane Kruger, Julian Schnabel, John Cleese, Jimmy Page, Vivienne Westwood, Takeshi Kitano and many others.\r\n ','https://kinoafisha.ua/upload/2019/01/films/8690/1z1nssul.png','https://m.media-amazon.com/images/M/MV5BYjY1MjQ5ZDItNGEyYi00NzJhLTgxMzctMjNhNTc5NjFhODg0XkEyXkFqcGdeQXVyNzYzODE5OTQ@._V1_.jpg'),(39,'Шлях додому','A Dog\'s Way Home','Пригоди','Adventure','Чарльз Мартін Сміт','Charles Martin Smith',000000000008,'Пригоди собачки Бели, яка здолала понад 600 кілометрів у пошуках свого хазяїна. На своєму шляху вона зіткнеться з різними пригодами та цікавими відкриттями.','A dog embarks on a 400-mile journey home after it\'s separated from its owner, Lucas, who is an aspiring medical student.','https://kinoafisha.ua/upload/2018/10/films/8598/1yzdz1bdput-domoy.jpg','https://www.filmoria.co.uk/wp-content/uploads/2018/11/A-Dogs-Way-Home-Poster.jpg'),(40,'Родина за хвилину','Instant Family','Комедія','Comedy','Шон Андерс','Sean Anders',0000000007.6,'Зворушлива та смішна історія усиновлення подружньою парою відразу трьох дітей, які перевертають колишнє життя родини з ніг на голову.','When Pete and Ellie decide to start a family, they stumble into the world of foster care adoption. They hope to take in one small child, but when they meet three siblings, including a rebellious 15-year-old girl, they find themselves speeding from zero to three kids overnight. Now, Pete and Ellie must try to learn the ropes of instant parenthood in the hope of becoming a family.','https://kinoafisha.ua/upload/2018/10/films/8571/1yznsyqjsemya-za-minutu.jpg','https://m.media-amazon.com/images/M/MV5BMTkzMzgzMTc1OF5BMl5BanBnXkFtZTgwNjQ4MzE0NjM@._V1_.jpg'),(41,'Крід II: Спадок Роккі Бальбоа','Creed II','Драма','Drama','Стівен Кейпл','Steven Caple',0000000007.7,'Чемпіон у напівважкій вазі адоніс Крід, що знаходиться під опікою Роккі Бальбоа, повинен зустрітися на рингу з Віктором Драго - сином боксера Івана Драго. ','In 1985, Russian boxer Ivan Drago killed former U.S. champion Apollo Creed in a tragic match that stunned the world. Against the wishes of trainer Rocky Balboa, Apollo\'s son Adonis Johnson accepts a challenge from Drago\'s son -- another dangerous fighter. Under guidance from Rocky, Adonis trains for the showdown of his life - a date with destiny that soon becomes his obsession. Now, Johnson and Balboa must confront their shared legacy as the past comes back to haunt each man.','https://kinoafisha.ua/upload/2018/03/films/8296/1540820205krid-2.jpg','https://upload.wikimedia.org/wikipedia/en/9/90/Creed_II_poster.png'),(42,'Назва1','Name1','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(43,'Назва2','Name2','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(44,'Назва3','Name3','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(45,'Назва4','Name4','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(46,'Назва5','Name5','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(47,'Назва6','Name6','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(48,'Назва7','Name7','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(49,'Назва8','Name8','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(50,'Назва9','Name9','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(51,'Назва10','Name10','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(52,'Назва11','Name11','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(53,'Назва12','Name12','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(54,'Назва13','Name13','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(55,'Назва14','Name14','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(56,'Назва15','Name15','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(57,'Назва16','Name16','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(58,'Назва17','Name17','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(59,'Назва18','Name18','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(60,'Назва19','Name19','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(61,'Назва20','Name20','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(62,'Назва21','Name21','Жанр','Genre','Режисер','Director',0000000007.7,'Опис','Description','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png','https://www.fyimusicnews.ca/sites/default/files/default_images/no-image-available.png'),(64,'adsf','asdf','asdff','adsfasdf','adfsadfasdf','asdfasfasdfafdsads',000000000010,'Опис','Опис англійською','fadsasdfasfadsfas','');
/*!40000 ALTER TABLE `films` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `halls`
--

DROP TABLE IF EXISTS `halls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `halls` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rows` int(11) DEFAULT NULL,
  `columns` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `halls`
--

LOCK TABLES `halls` WRITE;
/*!40000 ALTER TABLE `halls` DISABLE KEYS */;
INSERT INTO `halls` VALUES (1,15,15);
/*!40000 ALTER TABLE `halls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seances`
--

DROP TABLE IF EXISTS `seances`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `seances` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` timestamp NOT NULL,
  `duration` int(11) DEFAULT NULL,
  `film_id` int(11) NOT NULL,
  `price` int(11) DEFAULT NULL,
  `hall_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`start_time`),
  KEY `fk_Seance_Film1_idx` (`film_id`),
  KEY `fk_seances_halls1_idx` (`hall_id`),
  CONSTRAINT `fk_Seance_Film1` FOREIGN KEY (`film_id`) REFERENCES `films` (`id`),
  CONSTRAINT `fk_seances_halls1` FOREIGN KEY (`hall_id`) REFERENCES `halls` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seances`
--

LOCK TABLES `seances` WRITE;
/*!40000 ALTER TABLE `seances` DISABLE KEYS */;
INSERT INTO `seances` VALUES (30,'2019-12-16 15:04:00',120,28,100,1),(34,'2019-01-07 10:00:00',120,26,140,1),(35,'2019-01-07 12:00:00',120,26,120,1),(36,'2019-01-07 10:30:00',120,31,200,1),(37,'2019-01-08 07:00:00',120,37,200,1),(38,'2019-01-08 09:00:00',120,35,150,1),(39,'2019-01-08 11:00:00',120,39,130,1),(40,'2019-01-09 11:00:00',120,32,150,1),(41,'2019-01-09 13:00:00',120,41,200,1),(42,'2019-01-10 15:00:00',120,30,200,1);
/*!40000 ALTER TABLE `seances` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tickets` (
  `user_id` int(11) NOT NULL,
  `seance_id` int(11) NOT NULL,
  `row` int(11) DEFAULT NULL,
  `seat` int(11) DEFAULT NULL,
  KEY `fk_User_has_Seance_Seance1_idx` (`seance_id`),
  KEY `fk_User_has_Seance_User1_idx` (`user_id`),
  CONSTRAINT `fk_User_has_Seance_Seance1` FOREIGN KEY (`seance_id`) REFERENCES `seances` (`id`),
  CONSTRAINT `fk_User_has_Seance_User1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='			';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES (30,34,2,5),(30,34,1,5),(30,34,1,4),(30,34,3,7),(30,34,3,6),(30,36,8,8),(30,36,8,7),(30,36,8,6);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(14) DEFAULT NULL,
  `password` char(128) DEFAULT NULL,
  `role` varchar(45) NOT NULL,
  `money` mediumtext,
  `first_name` varchar(45) DEFAULT NULL,
  `first_name_en` varchar(45) DEFAULT NULL,
  `second_name` varchar(45) DEFAULT NULL,
  `second_name_en` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (28,'admin','e1840ff345d52dd3c53a449e7b3b154757861279cbbed51466c73a03a2a143f53c1b539589fff86d9273bdad0726122b1f8c961939ce1ba93c1cc3bf59d847ad','ADMIN','1000',NULL,NULL,NULL,NULL),(30,'qwerty','ee29532ebdb2e088668d479b94e67240053c640dc5422ceb33f916a5d13ebd3d3d9e35388ca390ff09e941c1a258a82b6139f35d356fa50ee5dcae7656f8ab8e','USER','543','','','',''),(32,'asdf','e1e57e9e9cf980cb9dd04a7bc40ef4547f872b7246386d19e97372697a3208b1ca664410bc551dd9b42957c8451c86f701ef0da33ece8946a0cf1c06837cf83a','USER','3910','','','',''),(54,'vvvvvv','9cd504ac51866e05087addbc7231494c208c24ceead230bb94b390be72e8ffcc954bb950ffb73ec3d35d9e7249f7a5614bec5b53e81d8be658ef1ace2e09e14a','USER','0','','','','');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-07 12:46:29
GRANT ALL PRIVILEGES ON db_cinema.* TO 'cinema_admin'@'localhost';