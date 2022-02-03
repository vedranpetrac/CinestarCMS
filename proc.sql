USE [CinestarCMS]
GO
/****** Object:  StoredProcedure [dbo].[checkAccountAuth]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[checkAccountAuth]
@username nvarchar(60),
@password nvarchar(64),
@level int out
as
if exists(select Username from Account where Username = @username and Password = @password)
begin
	select @level = Account.Level from Account where Username = @username and Password = @password
end
else
begin
 select @level = 0
end
GO
/****** Object:  StoredProcedure [dbo].[createActor]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[createActor]
	@FirstName NVARCHAR(90),
	@LastName NVARCHAR(90),
	@Id INT OUTPUT
AS 
BEGIN 
	INSERT INTO Actor(FirstName, LastName) VALUES(@FirstName, @LastName)
	SET @Id = SCOPE_IDENTITY()
END
GO
/****** Object:  StoredProcedure [dbo].[createDirector]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[createDirector]
	@FirstName NVARCHAR(90),
	@LastName NVARCHAR(90),
	@Id INT OUTPUT
AS 
BEGIN 
	INSERT INTO Director(FirstName, LastName) VALUES(@FirstName, @LastName)
	SET @Id = SCOPE_IDENTITY()
END
GO
/****** Object:  StoredProcedure [dbo].[createGenre]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[createGenre]
	@Name NVARCHAR(90),
	@Id INT OUTPUT
AS 
BEGIN 
	INSERT INTO Genre(Name) VALUES(@Name)
	SET @Id = SCOPE_IDENTITY()
END
GO
/****** Object:  StoredProcedure [dbo].[createMovie]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[createMovie]
	@Title NVARCHAR(300),
	@OriginalTitle NVARCHAR(300),
	@Description NVARCHAR(900),
	@Duration int,
	@PicturePath NVARCHAR(90),
	@Id INT OUTPUT
AS 
BEGIN 
	INSERT INTO Movie(Title,OriginalTitle,Duration,Description,PicturePath) VALUES(@Title, @OriginalTitle, @Duration, @Description, @PicturePath)
	SET @Id = SCOPE_IDENTITY()
END
GO
/****** Object:  StoredProcedure [dbo].[createMovieActor]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[createMovieActor]
	@IdMovie int,
	@IdActor int,
	@Id INT OUTPUT
AS 
BEGIN 
	INSERT INTO Movie_Actor(MovieID, ActorID) VALUES(@IdMovie, @IdActor)
	SET @Id = SCOPE_IDENTITY()
END
GO
/****** Object:  StoredProcedure [dbo].[createMovieDirector]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[createMovieDirector]
	@IdMovie int,
	@IdDirector int,
	@Id INT OUTPUT
AS 
BEGIN 
	INSERT INTO Movie_Director(MovieID, DirectorID) VALUES(@IdMovie, @IdDirector)
	SET @Id = SCOPE_IDENTITY()
END
GO
/****** Object:  StoredProcedure [dbo].[createMovieGenre]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[createMovieGenre]
	@IdMovie int,
	@IdGenre int,
	@Id INT OUTPUT
AS 
BEGIN 
	INSERT INTO Movie_Genre(MovieID, GenreID) VALUES(@IdMovie, @IdGenre)
	SET @Id = SCOPE_IDENTITY()
END
GO
/****** Object:  StoredProcedure [dbo].[deleteActor]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[deleteActor]
	@IdActor INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			Actor
	WHERE 
		IDActor = @IdActor
END
GO
/****** Object:  StoredProcedure [dbo].[deleteDirector]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[deleteDirector]
	@IdDirector INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			Director
	WHERE 
		IDDirector = @IdDirector
END
GO
/****** Object:  StoredProcedure [dbo].[deleteGenre]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[deleteGenre]
	@IdGenre INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			Genre
	WHERE 
		IDGenre = @IdGenre
END
GO
/****** Object:  StoredProcedure [dbo].[deleteMovie]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[deleteMovie]
	@IdMovie INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			Movie
	WHERE 
		IDMovie = @IdMovie
END
GO
/****** Object:  StoredProcedure [dbo].[deleteMovieActor]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[deleteMovieActor]
	@IdMovieActor INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			Movie_Actor
	WHERE 
		IDMovieActor = @IdMovieActor
END
GO
/****** Object:  StoredProcedure [dbo].[deleteMovieDirector]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[deleteMovieDirector]
	@IdMovieDirector INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			Movie_Director
	WHERE 
		IDMovieDirector = @IdMovieDirector
END
GO
/****** Object:  StoredProcedure [dbo].[deleteMovieGenre]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[deleteMovieGenre]
	@IdMovieGenre INT	 
AS 
BEGIN 
	DELETE  
	FROM 
			Movie_Genre
	WHERE 
		IDMovieGenre = @IdMovieGenre
END
GO
/****** Object:  StoredProcedure [dbo].[selectActor]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[selectActor]
	@IdActor INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		Actor
	WHERE 
		IDActor = @IdActor
END
GO
/****** Object:  StoredProcedure [dbo].[selectActors]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[selectActors]
AS 
BEGIN 
	SELECT * FROM Actor
END
GO
/****** Object:  StoredProcedure [dbo].[selectDirector]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[selectDirector]
	@IdDirector INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		Director
	WHERE 
		IDDirector = @IdDirector
END
GO
/****** Object:  StoredProcedure [dbo].[selectDirectors]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[selectDirectors]
AS 
BEGIN 
	SELECT * FROM Director
END
GO
/****** Object:  StoredProcedure [dbo].[selectGenre]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[selectGenre]
	@IdGenre INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		Genre
	WHERE 
		IDGenre = @IdGenre
END
GO
/****** Object:  StoredProcedure [dbo].[selectGenres]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[selectGenres]
AS 
BEGIN 
	SELECT * FROM Genre
END
GO
/****** Object:  StoredProcedure [dbo].[selectMovie]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[selectMovie]
	@IdMovie INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		Movie
	WHERE 
		IDMovie = @IdMovie
END
GO
/****** Object:  StoredProcedure [dbo].[selectMovieActors]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



create proc [dbo].[selectMovieActors]
@movieId int
as
select * from Actor
inner join Movie_Actor on Actor.IDActor = Movie_Actor.ActorID
where MovieID = @movieId
GO
/****** Object:  StoredProcedure [dbo].[selectMovieDirectors]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[selectMovieDirectors]
@movieId int
as
select * from Director
inner join Movie_Director on Director.IDDirector = Movie_Director.DirectorID
where MovieID = @movieId
GO
/****** Object:  StoredProcedure [dbo].[selectMovieGenres]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


create proc [dbo].[selectMovieGenres]
@movieId int
as
select * from Genre
inner join Movie_Genre on Genre.IDGenre = Movie_Genre.GenreID
where MovieID = @movieId
GO
/****** Object:  StoredProcedure [dbo].[selectMovies]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[selectMovies]
AS 
BEGIN 
	SELECT * FROM Movie
END
GO
/****** Object:  StoredProcedure [dbo].[updateActor]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[updateActor]
	@IdActor INT,
	@FirstName NVARCHAR(90),
	@LastName NVARCHAR(90)
AS 
BEGIN 
	UPDATE Actor SET 
		FirstName = @FirstName,
		LastName = @LastName
		WHERE 
		IDActor = @IdActor
END
GO
/****** Object:  StoredProcedure [dbo].[updateDirector]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[updateDirector]
	@IdDirector INT,
	@FirstName NVARCHAR(90),
	@LastName NVARCHAR(90)
AS 
BEGIN 
	UPDATE Director SET 
		FirstName = @FirstName,
		LastName = @LastName
		WHERE 
		IDDirector = @IdDirector
END
GO
/****** Object:  StoredProcedure [dbo].[updateGenre]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[updateGenre]
	@IdGenre INT,
	@Name NVARCHAR(90)
AS 
BEGIN 
	UPDATE Genre SET 
		Name = @Name
		WHERE 
		IDGenre = @IdGenre
END
GO
/****** Object:  StoredProcedure [dbo].[updateMovie]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[updateMovie]
	@IdMovie INT,
	@Title NVARCHAR(300),
	@OriginalTitle NVARCHAR(300),
	@Description NVARCHAR(900),
	@Duration int,
	@PicturePath NVARCHAR(90)
AS 
BEGIN 
	UPDATE Movie SET 
		Title = @Title,
		OriginalTitle = @OriginalTitle,
		Description = @Description,
		Duration = @Duration,
		PicturePath = @PicturePath
	WHERE 
		IDMovie = @IdMovie
END
GO
/****** Object:  StoredProcedure [dbo].[updateMovieActor]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[updateMovieActor]
	@IdMovieActor INT,
	@IdMovie int,
	@IdActor int
AS 
BEGIN 
	UPDATE Movie_Actor SET 
		MovieID = @IdMovie,
		ActorID = @IdActor
		WHERE 
		IDMovieActor = @IdMovieActor
END
GO
/****** Object:  StoredProcedure [dbo].[updateMovieDirector]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[updateMovieDirector]
	@IdMovieDirector INT,
	@IdMovie int,
	@IdDirector int
AS 
BEGIN 
	UPDATE Movie_Director SET 
		MovieID = @IdMovie,
		DirectorID = @IdDirector
		WHERE 
		IDMovieDirector = @IdMovieDirector
END
GO
/****** Object:  StoredProcedure [dbo].[updateMovieGenre]    Script Date: 1/29/2022 1:21:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[updateMovieGenre]
	@IdMovieGenre INT,
	@IdMovie int,
	@IdGenre int
AS 
BEGIN 
	UPDATE Movie_Genre SET 
		MovieID = @IdMovie,
		GenreID = @IdGenre
		WHERE 
		IDMovieGenre = @IdMovieGenre
END
GO
