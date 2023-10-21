import math
import pygame
import random
from pygame import mixer
import time

# initialize the pygame
pygame.init()
clock = pygame.time.Clock()

# creating the screen window
screen = pygame.display.set_mode((800, 600))

# background image
background = pygame.image.load('background.png')
mixer.music.load('background.wav')
mixer.music.play()
mixer.music.set_volume(0.4)
# setting the window title
pygame.display.set_caption("Space Invaders")

# player
playerImg = pygame.image.load('player.png')
playerX = 370
playerY = 480
# change variable for player movement
playerX_change = 0
playerY_change = 0

# enemy
enemyImg = []
enemyX = []
enemyY = []
enemyX_change = []
enemyY_change = []
num_of_enemies = 10
for i in range(num_of_enemies):
    enemyImg.append(pygame.image.load('enemy.png'))
    enemyX.append(random.randint(0, 735))
    enemyY.append(random.randint(10, 100))
    # change variable for enemy movement
    enemyX_change.append(1.8)
    enemyY_change.append(40)

# score
score_value = 0
font = pygame.font.Font('FakeHope.ttf', 40)
textX = 10
textY = 10
# game over Text
over_font = pygame.font.Font('Bubblegum.ttf', 96)
quote_font = pygame.font.Font('Bubblegum.ttf', 32)


def game_over_text():
    over_text = over_font.render("GAME OVER", True, (255, 255, 255))
    quote_text = quote_font.render("Better luck next time! ", True, (255, 255, 255))
    screen.blit(over_text, (170, 250))
    screen.blit(quote_text, (240, 360))


def show_score(x, y):
    score = font.render("Score: " + str(score_value), True, (255, 255, 255))
    screen.blit(score, (x, y))


# bullet
bulletImg = pygame.image.load('bullet.png')
bulletX = 0
bulletY = 480
# change variable for player movement
bulletX_change = 0
bulletY_change = 10
# ready: You can't see the bullet on the screen
# fire: the bullet is currently on scren
bullet_state = "ready"


def fire_bullet(x, y):
    global bullet_state
    bullet_state = "fire"
    screen.blit(bulletImg, (x + 16, y + 10))


def isCollision(enemyX, enemyY, bulletX, bulletY):
    distance = math.sqrt((math.pow(enemyX - bulletX, 2)) + (math.pow(enemyY - bulletY, 2)))
    if distance < 27:
        return True
    else:
        return False


# player initilisation
# playerImg = pygame.image.load('player.png')
# playerX = 370
# playerY = 480
# # change variable for player movement
# playerX_change = 0
# playerY_change = 0


def player(x, y):
    screen.blit(playerImg, (x, y))


def enemy(x, y, i):
    screen.blit(enemyImg[i], (x, y))


# Game Loop
running = True
while running:
    # set the background color
    screen.fill((0, 0, 0))
    screen.blit(background, (0, 0))
    # starting of for loop
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
        # changing player position on key stroke
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_LEFT:
                playerX_change = -5
            if event.key == pygame.K_RIGHT:
                playerX_change = 5
            # bullet is fired when SPACE BAR is pressed
            if event.key == pygame.K_SPACE:
                if bullet_state is "ready":
                    bullet_Sound = mixer.Sound('laser.wav')
                    bullet_Sound.play()
                    bullet_Sound.set_volume(0.5)
                    # get the current x coordinate of the spaceship
                    bulletX = playerX
                    fire_bullet(playerX, bulletY)

        # checking for keyup
        if event.type == pygame.KEYUP:
            if event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT:
                playerX_change = 0

    # automatic bullet firing

    # updating player position
    playerX += playerX_change

    # Restricting the player movement ie player should not go beyond the screen
    if playerX <= 0:
        playerX_change = 5
    elif playerX >= 736:
        playerX_change = -5

    # Restricting the enemy movement ie player should not go beyond the screen

    # Bullet movement
    if bulletY <= 0:
        bulletY = 480
        bullet_state = "ready"
    if bullet_state is "fire":
        fire_bullet(bulletX, bulletY)
        bulletY -= bulletY_change

    # enemy movement
    for i in range(num_of_enemies):
        # game over
        if enemyY[i] > 420:
            for j in range(num_of_enemies):
                enemyY[j] = 2000
            game_over_text()
            break
        enemyY += enemyY_change
        enemyX[i] += enemyX_change[i]
        if enemyX[i] <= 0:
            enemyX_change[i] = 1.8
            enemyY[i] += enemyY_change[i]
        elif enemyX[i] >= 736:
            enemyX_change[i] = -1.8
            enemyY[i] += enemyY_change[i]

        # Collision
        collision = isCollision(enemyX[i], enemyY[i], bulletX, bulletY)
        if collision:
            explosion_Sound = mixer.Sound('explosion.wav')
            explosion_Sound.play()
            explosion_Sound.set_volume(0.3)
            bulletY = 480
            bullet_state = "ready"
            score_value += 1
            enemyX[i] = random.randint(0, 735)
            enemyY[i] = random.randint(10, 100)
        enemy(enemyX[i], enemyY[i], i)

    # calling player function
    player(playerX, playerY)
    # calling player function
    show_score(textX, textY)
    pygame.display.update()
