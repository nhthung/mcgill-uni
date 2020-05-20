import numpy as np
import random
import matplotlib.pyplot as plt
import pickle, gzip, urllib.request, json

#To ensure reproducibility
random.seed(11)
np.random.seed(11)

# Load the dataset
urllib.request.urlretrieve("http://deeplearning.net/data/mnist/mnist.pkl.gz", "mnist.pkl.gz")
with gzip.open('mnist.pkl.gz', 'rb') as f:
    train_set, valid_set, test_set = pickle.load(f, encoding='latin1')
    
train_images = train_set[0]
train_labels = train_set[1]

test_images = test_set[0]
test_labels = test_set[1]

def sig(x):
    '''
    Sigmoid function
    '''
    return 1/(1 + np.exp(-x))

def plot(image, label=None):
    '''
    Plot image
    '''
    if len(image.shape) == 1:
        size = int(np.sqrt(image.shape[0]))
        image = image.reshape((size, size))
    elif image.shape[1] == 1:
        size = int(np.sqrt(image.shape[0]))
        image = image.reshape((size, size))
    if label:
        plt.title(f'Label is {label}')
    plt.imshow(image, cmap='gray')
    plt.show()
    
def to_binary(images, threshold=0.5):
    '''
    Convert images to binary
    '''
    images[images >= threshold] = 1
    images[images < threshold] = 0
    return images

# Convert all images to binary
to_binary(train_images)
to_binary(test_images)

class BoltzmannMachine:
    """
    Define a class for a boltzmann machine here.
    You must also define the functions below, but you can add additional arguments, etc.
    Also, note that you are welcome to write your own helper functions. 
    Reminder: you should use numpy functions for vector and matrix operations. No autograd!
    """
    
    # The initialization function for the boltzmann machine
    def __init__(self, train_images, test_images, H_size=None, H=None):
        """
        Comment!!!! Your marks depend on it!
        """
        # All activations
        self.A = self.init_A(train_images, H_size, H)        
        
        # Visible and hidden units
        self.V = self.A[: train_images[0].shape[0], :]
        self.H = self.A[train_images[0].shape[0] :, :]
        
        # Total number of units
        self.n = self.A.shape[0]
        
        # Random weights
        self.W = self.init_W()
        
        # Random thresholds
        self.Theta = np.random.randn(self.n, 1)
        
        # Train and test images
        self.train_images = train_images
        self.test_images = test_images
        self.train_size = train_images.shape[0]

        # Number of visible units
        self.V_size = self.V.shape[0]

        # Number of hidden units
        self.H_size = self.H.shape[0]
        
    # The function for Gibbs sampling
    def gibbs(self, AA, A, phase, gibbs_iters, T, verbose):
        '''
        Gibbs sampling
        '''        
        # Chose gibbs_iters random units
        units = np.random.randint(
            low=self.V_size if phase == 0 else 0, # During wake phase, update hidden units only
            high=self.n,
            size=gibbs_iters)
        
        At = self.A.T
        iters_per_img = int(gibbs_iters / self.train_size)
        
#         print('Energy before:', self.E())
        
        for i in range(gibbs_iters):
            if phase == 0 and i % iters_per_img == 0:
#             if phase == 0:
                img_idx = int(i/iters_per_img)
                self.clamp(img_idx)
                
                if verbose and (img_idx+1) % 25000 == 0:
                    print(f'At train image {img_idx}...')
            
            # Probability that unit i is activated        
            pai = self.pai(units[i],T)
            
            # Update state of unit i
            self.A[units[i]] = 1 if np.random.rand() <= pai else 0
            At[0, units[i]] = self.A[units[i], 0]

            # Update cooactivaion and activation count
            AA[phase, At[0]==1] += At
            A[phase] += self.A
        
#         print('Energy after:', self.E())
            
        # Average cooactivaion and activation count
        AA[phase] /= gibbs_iters
        A[phase] /= gibbs_iters
        
        return AA, A
    
    # The function for training the boltzmann machine
#     def train(self, anneal_sched=[1], T_iterations=2**8, gibbs_iterations=None, eta=1e-10, verbose=False):
    def train(self, anneal_sched=[1], iters=100, gibbs_iters=None, eta=1e-10, verbose=False):
        '''
        for T in anneal_sched:
            # Wake phase
            for img in train_images:
                update one neuron
                update coactivation matrix
            
            # Dream phase
            Randomize
            
            # Parameter update
            W = (aa_wake - aa_dream)/T
            
        Print loss values on training set.
        '''
        if not gibbs_iters:
            gibbs_iters[wake] = self.train_size * 10
            gibbs_iters[dream] = self.n * 100
        
        # Coactivation matrices:
        #     AA[0,i,j] = # times ai == aj == 1 during wake phase
        #     AA[1,i,j] = # times ai == aj == 1 during dream phase
        AA = np.zeros((2, self.n, self.n))

        # Activition count:
        #     A[0, i] = # times ai == 1 during wake phase
        #     A[1, i] = # times ai == 1 during dream phase
        A = np.zeros((2, self.n, 1))
        
        # Constant term of loss
        L_const = np.log(1/self.train_size)
        
        wake, dream = 0, 1
        
        # for T in anneal_sched:
        # for i in range(T_iterations):

        iters_per_T = int(iters / len(anneal_sched))

        for i in range(iters):
            if verbose:
                print('Iteration:', i)
            T = anneal_sched[int(i / iters_per_T)]
 
            for phase in [wake, dream]:
                if verbose:
                    print(f'{"Wake" if phase == 0 else "Dream"} phase')

                # Gibbs sampling
                self.gibbs(AA, A, phase, gibbs_iters[phase], T, verbose)

                # Reset activations by randomizing
                self.reset_A()

            # Update weights and biases
#             self.W += (AA[wake] - AA[dream])/T
#             self.Theta += (A[wake] - A[dream])/T
            self.W -= (AA[wake] - AA[dream])/T
            self.Theta -= (A[wake] - A[dream])/T

            # Calculate loss

            # Probabilities that visible units are activated
            pav_dream = self.pav(A[dream], T)

            # Sum of log probababilities of each image
            log_pv_dream = 0
            for img in self.train_images:
                log_pv_dream += self.log_pvk(pav_dream, img)
            log_pv_dream /= self.train_size

            # Print loss
            L = L_const - log_pv_dream
#                 L = L_const + log_pv_dream
            print('Loss:', L, '\n')

            # Reset co-activation matrices and average activation vectors
            AA[:] = 0
            A[:] = 0
    
    # Function to clamp image to visible units
    def clamp(self, image):
        '''
        Clamp image to visible units
        '''
        if isinstance(image, int):
            image = self.train_images[image]
        self.V[:] = image.reshape((image.shape[0], 1))[:]
    
    
    # The function for testing the boltzmann machine
    def test(self, test_images):
        '''
        This function must print out the loss values on the test set.
        Comment!!!! Your marks depend on it!
        '''
        pass
    
    # The function for testing the boltzmann machine
    def test(self):
        """
        This function must return a numpy array of generated images.
        Comment!!!! Your marks depend on it!
        """
        pass
    
    def pai(self, i, T):
        '''
        Probability that unit i is activated (ai = 1)
        '''
        WiA = self.W[i,:].dot(self.A)
#         print('T:', T)
        if T == 0:
            print('WHAT THE')
        return sig((WiA + self.Theta[i,:])/T)[0]

    def log_pvk(self, pav, img):
        '''
        Log probability of image
        '''
        pav[img==0] = 1 - pav[img==0]
        log_pvk = np.sum(np.log(pav))
        return log_pvk
    
    def pav(self, A=None, T=1):
        '''
        Probabilities that visible units are activated
        given the hidden units + weights
        '''
        if A is None:
            A = self.A
            
        WV = self.W[:self.V_size,:]
        ThetaV = self.Theta[:self.V_size,:]
        
        return sig((WV.dot(A) + ThetaV)/T)
        
    def init_A(self, train_images, H_size, H):
        '''
        Initizialize binary state/activation of all units
        '''
#         V = np.random.randn(train_images[0].shape[0], 1)
        V = np.random.randint(2, size=(train_images[0].shape[0], 1))
        
        if H is None:
            try:
#                 H = np.random.randn(H_size, 1)
                H = np.random.randint(2, size=(H_size, 1))
            except:
                raise ValueError('If H array not specified, specify H_size as int')
        
        A = np.append(V, H, axis=0)
#         to_binary(A, threshold=0)
        
        return A
    
    def reset_A(self):
#         self.A[:] = np.random.randn(self.n, 1)

        self.A[:] = np.random.randint(2, size=(self.n, 1))[:]
#         to_binary(self.A, threshold=0)
    
    def init_W(self):
        '''
        Initialize weights matrix W
        '''
        # Randomize weights according to Gaussian distribution
        W = np.random.randn(self.n, self.n)
        
        # Symmetric weights: Wij = Wji
        W = np.tril(W) + np.tril(W, -1).T
        
        # Each node has no weight for itself: Wii = 0
        np.fill_diagonal(W, 0)
        
        # Visible units have no weights connecting between themselves
        W[:self.V.shape[0], :self.V.shape[0]] = 0 
        
        return W
    
    def E(self):
        '''
        Energy of current state
        '''
        # (W * A).T * A + Theta.T * A
        W, A, Theta = self.W, self.A, self.Theta
        WAtA = W.dot(A).T.dot(A)
        ThetatA = Theta.T.dot(A)
        E = -WAtA + ThetatA
        return E

# Create a Boltzmann machine and train it
boltzmann = BoltzmannMachine(train_images, test_images, H_size=10)

# anneal_sched = [40, 35, 30, 25, 20, 15, 12, 10]
# anneal_sched = [40, 40, 60, 60, 80, 100]
# anneal_sched = [i for i in range(30, 101, 10)]
anneal_sched = [i for i in range(1,101)]
print(anneal_sched)
# anneal_sched = [2**16, 2**8, 2**6, 2**4, 2**2, 1]
boltzmann.train(
    anneal_sched,
    iters=100,
    gibbs_iters=[boltzmann.train_size*1, boltzmann.n*1],
    verbose=True
)