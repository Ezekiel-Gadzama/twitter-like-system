# Not working 


import unittest
import requests

class TestTwitterLikeSystem(unittest.TestCase):
    BASE_URL = 'http://localhost:8080/TwitterLikeSystem'  # Replace with your actual server URL
    
    def setUp(self):
        self.register_url = f'{self.BASE_URL}/users/register'
        self.post_url = f'{self.BASE_URL}/messages/post'
        self.like_url = f'{self.BASE_URL}/likes/like'
        self.view_url = f'{self.BASE_URL}/view'

    def test_user_registration_and_posts(self):
        # Register 4 users
        users = ['user1', 'user2', 'user3', 'user4']
        for user in users:
            response = requests.post(self.register_url, json={'username': user})
            self.assertEqual(response.status_code, 200)
            self.assertIn('User registered successfully', response.text)
        
        # Each user posts 3 messages
        for user in users:
            for i in range(3):
                response = requests.post(self.post_url, json={'username': user, 'message': f'Message {i} from {user}'})
                self.assertEqual(response.status_code, 200)
                self.assertIn('Message posted successfully', response.text)
        
        # Non-registered user tries to post a message
        response = requests.post(self.post_url, json={'username': 'not_registered', 'message': 'This should fail'})
        self.assertNotEqual(response.status_code, 200)
        self.assertIn('User not registered', response.text)

    def test_like_post(self):
        # Registered user likes a post
        response = requests.post(self.like_url, json={'username': 'user1', 'post_id': 1})
        self.assertEqual(response.status_code, 200)
        self.assertIn('Post liked successfully', response.text)

        # Non-registered user tries to like a post
        response = requests.post(self.like_url, json={'username': 'not_registered', 'post_id': 1})
        self.assertNotEqual(response.status_code, 200)
        self.assertIn('User not registered', response.text)
    
    def test_view_posts(self):
        # View all posts
        response = requests.get(self.view_url)
        self.assertEqual(response.status_code, 200)
        self.assertIn('Message', response.text)  # Assuming posts are listed with 'Message' in their text

    def test_scalability(self):
        # Simulate multiple users posting messages (scalability check)
        for i in range(100):  # Simulate 100 posts
            response = requests.post(self.post_url, json={'username': 'user1', 'message': f'Scalability test message {i}'})
            self.assertEqual(response.status_code, 200)
        
        # Check if we can still view the last 10 messages
        response = requests.get(self.view_url, params={'limit': 10})
        self.assertEqual(response.status_code, 200)
        self.assertEqual(len(response.json()), 10)  # Assuming response is a JSON array of messages

if __name__ == '__main__':
    unittest.main()
