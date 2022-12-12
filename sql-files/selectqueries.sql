create DATABASE `blog-app`;
use `blog-app`;
select * from s_users;
select * from s_category;
select * from s_posts
-- where x_post_title like '%6%'
;
select count(*) from s_posts;

desc s_users;
desc s_category;
desc s_posts;