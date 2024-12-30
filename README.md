# PostBook

PostBook is a simple Java Swing-based social media application inspired by Facebook. This project is developed as part of a database class project. Users can create accounts, make posts, and connect with friends.

## Features
- **User Management**: Create accounts and log in using a username and password or sign up.
- **Posting**: Share posts with text content and view posts by your friends.
- **Friendships**: Connect with other users and maintain a list of friends.

## Database Schema
PostBook uses a relational database with the following three tables:

### Users
| Column      | Type    | Description                     |
|-------------|---------|---------------------------------|
| username    | VARCHAR | Unique identifier for a user.  |
| password    | VARCHAR | User's password (hashed).      |
| name        | VARCHAR | Full name of the user.         |

### Posts
| Column      | Type     | Description                              |
|-------------|----------|------------------------------------------|
| user_id     | INTEGER  | Foreign key referencing `users` table.  |
| post_text   | TEXT     | Content of the post.                    |
| post_date   | DATETIME | Date and time the post was created.     |

### Friendships
| Column      | Type    | Description                              |
|-------------|---------|------------------------------------------|
| user1_id    | INTEGER | Foreign key referencing `users` table.  |
| user2_id    | INTEGER | Foreign key referencing `users` table.  |

## Technologies Used
- **Programming Language**: Java
- **GUI Framework**: Swing
- **Database**: PostgreSQL

## Screenshots
### Log In
![Log In Page](https://github.com/user-attachments/assets/0192babb-c3cd-4cb2-94b9-362cedee633d)
### Sign Up
![Sign Up Page](https://github.com/user-attachments/assets/01e00dfd-2db8-4c9e-b89a-ff33ee7cb014)
### Home Page
![Home Page](https://github.com/user-attachments/assets/931356cd-2222-46bd-a5e8-ea05f0fddac9)
### Friends List
![Friends List](https://github.com/user-attachments/assets/6434889d-4a3d-49ec-bd6a-e046b92dca3f)
### My Account
![My Account](https://github.com/user-attachments/assets/74e34385-c848-4cac-8d08-6e677da0ef26)
### Search
![Search Users](https://github.com/user-attachments/assets/e4d6aa92-d3be-4812-811a-94f3c74fc258)

## Future Improvements
- Add profile pictures and user bios.
- Implement post likes and comments.
- Enhance the UI with more modern styling.

